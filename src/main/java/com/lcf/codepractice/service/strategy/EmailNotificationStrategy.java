package com.lcf.codepractice.service.strategy;

import org.springframework.stereotype.Component;

/**
 * @author Luchunfang
 * 邮件发送
 */
@Component
public class EmailNotificationStrategy implements NotificationStrategy {

    @Override
    public String getType() {
        return "EMAIL";
    }

    @Override
    public void send(String receiver, String content) {
        // 模拟邮件发送逻辑
        System.out.println("【邮件通知】正在发送至：" + receiver);
        System.out.println("【邮件内容】：" + content);
    }
}
