package com.lcf.codepractice.controller;

import com.lcf.codepractice.common.model.Result;
import com.lcf.codepractice.model.dto.BatchNotifyDTO;
import com.lcf.codepractice.model.vo.BatchNotifyResultVO;
import com.lcf.codepractice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Luchunfang
 */
@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;

    /**
     * 单个通知发送
     */
    @PostMapping("/send")
    public Result<Void> send(
            @RequestParam String type,
            @RequestParam String receiver,
            @RequestParam String content) {
        notificationService.sendNotification(type, receiver, content);
        return Result.success();
    }

    /**
     * 批量通知发送
     */
    @PostMapping("/batch")
    public Result<BatchNotifyResultVO> batchSend(@Valid @RequestBody BatchNotifyDTO batchNotifyDTO) {
        BatchNotifyResultVO result = notificationService.batchSendNotification(batchNotifyDTO);
        if (result.getFailCount() > 0) {
            return Result.success("部分通知发送失败", result);
        }
        return Result.success("全部通知发送成功", result);
    }

}
