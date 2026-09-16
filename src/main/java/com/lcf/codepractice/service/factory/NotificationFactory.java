package com.lcf.codepractice.service.factory;

import com.lcf.codepractice.service.strategy.NotificationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Luchunfang
 * 通知工厂
 */
@Component
public class NotificationFactory {

    // 存储所有策略的 Map，Key 是通知类型（如 EMAIL），Value 是策略实例
    private final Map<String, NotificationStrategy> strategyMap;

    // Spring 会自动将所有实现了 NotificationStrategy 接口的 Bean 注入到这个 List 中
    public NotificationFactory(List<NotificationStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(NotificationStrategy::getType, Function.identity()));
    }

    /**
     * 根据类型获取对应的策略实例
     */
    public NotificationStrategy getStrategy(String type) {
        NotificationStrategy strategy = strategyMap.get(type.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的通知类型: " + type);
        }
        return strategy;
    }
}
