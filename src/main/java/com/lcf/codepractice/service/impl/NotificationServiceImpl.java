package com.lcf.codepractice.service.impl;

import com.lcf.codepractice.model.dto.BatchNotifyDTO;
import com.lcf.codepractice.model.vo.BatchNotifyResultVO;
import com.lcf.codepractice.service.NotificationService;
import com.lcf.codepractice.service.factory.NotificationFactory;
import com.lcf.codepractice.service.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @author Luchunfang
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory notificationFactory;

    private final Executor notifyExecutor;

    @Override
    public void sendNotification(String type, String receiver, String content) {
        try {
            sendWithRetry(type, receiver, content);
            log.info("通知发送成功: type={}, receiver={}", type, receiver);
        } catch (Exception e) {
            log.error("通知发送失败: type={}, receiver={}", type, receiver, e);
            throw e;
        }
    }

    /**
     * 带重试机制的发送
     * 只重试网络类异常，避免业务异常被误重试
     */
    @Retryable(
            value = {ConnectException.class, SocketTimeoutException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 5000)
    )
    public void sendWithRetry(String type, String receiver, String content) {
        NotificationStrategy strategy = notificationFactory.getStrategy(type);
        strategy.send(receiver, content);
    }

    @Override
    public BatchNotifyResultVO batchSendNotification(BatchNotifyDTO batchNotifyDTO) {
        List<String> receivers = batchNotifyDTO.getReceivers();

        // 异步并行发送
        List<CompletableFuture<BatchNotifyResultVO.FailDetail>> futures = receivers.stream()
                .map(receiver -> CompletableFuture.supplyAsync(() -> {
                    try {
                        sendNotification(batchNotifyDTO.getType(), receiver, batchNotifyDTO.getContent());
                        return null;
                    } catch (Exception e) {
                        return new BatchNotifyResultVO.FailDetail(
                                receiver,
                                e.getMessage() != null ? e.getMessage() : "发送失败"
                        );
                    }
                }, notifyExecutor))
                .collect(Collectors.toList());

        // 等待所有任务完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 收集失败结果
        List<BatchNotifyResultVO.FailDetail> failDetails = futures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new BatchNotifyResultVO(
                receivers.size(),
                receivers.size() - failDetails.size(),
                failDetails.size(),
                failDetails
        );
    }

}
