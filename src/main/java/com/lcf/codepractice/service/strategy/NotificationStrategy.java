package com.lcf.codepractice.service.strategy;

/**
 * @author Luchunfang
 * 统一通知策略接口
 */
public interface NotificationStrategy {
    /**
     * 获取当前策略支持的通知类型（用于工厂匹配）
     */
    String getType();


    /**
     * 执行发送动作
     * @param receiver 接收者（手机号/邮箱/群ID等）
     * @param content  发送内容
     */
    void send(String receiver, String content);
}
