package com.websocket.client;

import com.websocket.client.listener.WebsocketListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@SpringBootApplication
public class ClientApplication {
	private static String URL = "ws://localhost:8080/ws";
	public static void main(String[] args) {
		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		StompSessionHandler sessionHandler = new WebsocketListener();
		stompClient.connect(URL, sessionHandler);

		SpringApplication.run(ClientApplication.class, args);
	}

}
