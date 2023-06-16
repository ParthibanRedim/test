package com.websocket.client.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;


@Slf4j
@Configuration
public class WebsocketListener extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders headers) {
        log.info("Client connected: headers {}", headers);
        session.subscribe("/topic/client", this);
        String message = "one-time message from client";
        log.info("Client sends: {}", message);
        session.send("/topic/client1", message);
        session.setAutoReceipt(true);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Client received: payload {}, headers {}", payload, headers);
    }
}
