package io.gitee.kewen.yuce.gateway.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GlobalFilter implements GatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            log.info("request entering gateway {}", exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }

    @Override
    public Class<Object> getConfigClass() {
        return Object.class;
    }

    @Override
    public String name() {
        return "GlobalFilter";
    }
}
