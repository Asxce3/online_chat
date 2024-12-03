package org.example.websocket_server.websocket.config;


import org.example.websocket_server.websocket.interceptor.ChatHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class ChatConfig implements WebSocketConfigurer {
    @Autowired
    private WebSocketHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/ws")
                .setAllowedOrigins("*")
                .addInterceptors(new ChatHandshakeInterceptor());

    }

}
