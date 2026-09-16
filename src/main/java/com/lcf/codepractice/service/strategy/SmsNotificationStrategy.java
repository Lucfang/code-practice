package com.lcf.codepractice.service.strategy;

import org.springframework.stereotype.Component;

/**
 * @author Luchunfang
 * 短信发送
 */
@Component
public class SmsNotificationStrategy implements NotificationStrategy {

    @Override
    public String getType() {
        return "SMS";
    }

    @Override
    public void send(String receiver, String content) {
        // 模拟短信发送逻辑
        System.out.println("【短信通知】正在发送至：" + receiver);
        System.out.println("【短信内容】：" + content);
    }
}
