package com.app.dodamdodam.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        /*.setAllowedOrigins("*")
            : ws프로토콜 /ws/chat 하위의 모든 uri에서 chatHandler를 사용한다는 의미*/
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
        /*.addInterceptors(new HttpSessionHandshakeInterceptor())
            : 핸드 쉐이크를 할 때, http의 session을 가져오기 위한 인터셉터를 추가 없어도 상관없다.*/
    }

}
