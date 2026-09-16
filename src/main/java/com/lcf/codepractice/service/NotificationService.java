package com.lcf.codepractice.service;

import com.lcf.codepractice.model.dto.BatchNotifyDTO;
import com.lcf.codepractice.model.vo.BatchNotifyResultVO;

/**
 * @author Luchunfang
 */
public interface NotificationService {

    /**
     * 发送单个通知
     */
    void sendNotification(String type, String receiver, String content);

    /**
     * 批量发送通知
     */
    BatchNotifyResultVO batchSendNotification(BatchNotifyDTO batchNotifyDTO);
}
