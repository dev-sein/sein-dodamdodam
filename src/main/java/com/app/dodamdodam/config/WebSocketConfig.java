package com.app.dodamdodam.config;

import com.app.dodamdodam.handler.ChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private final ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        /*.setAllowedOrigins("*")
            : ws프로토콜 /ws/chat 하위의 모든 uri에서 chatHandler를 사용한다는 의미*/
        registry.addHandler(chatHandler, "/ws/chat").setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
        // interceptor for adding httpsession into websocket session
        /*.addInterceptors(new HttpSessionHandshakeInterceptor())
            : 핸드 쉐이크를 할 때, http의 session을 가져오기 위한 인터셉터를 추가 없어도 상관없다.*/
    }

}
