package io.gitee.kewen.yuce.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取token头
        String token = request.getHeaders().getFirst("Authorization");
        String requestUrl = String.valueOf(request.getPath());
        if ("/portal/user/login".equals(requestUrl)){
            return chain.filter(exchange);
        }
        if (token == null || !token.startsWith("Bearer ")) {
            ServerHttpResponse response = exchange.getResponse();
            DataBufferFactory bufferFactory = response.bufferFactory();
            DataBuffer buffer = bufferFactory.wrap("Token is missing or invalid.".getBytes(StandardCharsets.UTF_8));

            // 设置响应状态码为401 Unauthorized
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            // 发送响应体并关闭响应
            return response.writeWith(Mono.just(buffer))
                    .then() // 发送完响应体后继续执行
                    .then(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is missing or invalid.")));
        }
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String jsonString = stringRedisTemplate.opsForValue().get("login:" + token);
            if (Objects.isNull(jsonString)){
                throw new RuntimeException("token过期了,请重新登录");
            }else {
                stringRedisTemplate.opsForValue().set("login:" + token, jsonString, 1800, TimeUnit.SECONDS);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
