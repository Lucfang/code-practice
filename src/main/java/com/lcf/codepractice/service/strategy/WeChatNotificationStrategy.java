package com.lcf.codepractice.service.strategy;

import org.springframework.stereotype.Component;

/**
 * @author Luchunfang
 * 企微发送
 */
@Component
public class WeChatNotificationStrategy implements NotificationStrategy {
    @Override
    public String getType() {
        return "WECHAT";
    }

    @Override
    public void send(String receiver, String content) {
        // 模拟企微机器人发送逻辑
        System.out.println("【企微通知】正在发送至群：" + receiver);
        System.out.println("【企微内容】：" + content);
    }
}
