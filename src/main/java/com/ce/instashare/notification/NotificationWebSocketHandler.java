package com.ce.instashare.notification;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

public class NotificationWebSocketHandler implements WebSocketHandler {

    

    public NotificationWebSocketHandler() {
        
    }

    @Override
    public List<String> getSubProtocols() {
        return Arrays.asList("test");
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String protocol = session.getHandshakeInfo().getSubProtocol();
        WebSocketMessage message = session.textMessage("hola");
        return doSend(session, Mono.just(message));
    }

    private Mono<Void> doSend(WebSocketSession session, Publisher<WebSocketMessage> output) {
        return session.send(Mono.delay(Duration.ofMillis(100)).thenMany(output));
    }

}