package com.ce.instashare.notification;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.stream.Stream;

import javax.security.auth.callback.TextOutputCallback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.ce.instashare.dto.common.response.GenericResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Component
public class NotificationWebSocketHandler implements WebSocketHandler {

	public static Logger logger = LogManager.getLogger(NotificationWebSocketHandler.class);
	static ScheduledThreadPoolExecutor scheduler =  new ScheduledThreadPoolExecutor(5);
	private static Map<String,Sinks.Many<String>> sessions = new HashMap<String,Sinks.Many<String>>();
    public NotificationWebSocketHandler() {
        
    }

    @Override
    public List<String> getSubProtocols() {
        return new ArrayList<String>();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
            
    	session.getHandshakeInfo().getSubProtocol();    	
    	Sinks.Many<String> pub = Sinks.many().unicast().onBackpressureBuffer();    	
    	return session
				.send(pub.asFlux().map(session::textMessage))
				.and(session.receive()
						.map(msg -> this.handleMessage(msg.getPayloadAsText(), pub))
						.map(session::textMessage) 
					).then();
            	
    }
    public void notifyUser(String userId,GenericResponseDTO response) throws JsonProcessingException {
    	synchronized (sessions) {
    		if(sessions.containsKey(userId)) {    		
        		Sinks.Many<String> s = sessions.get(userId);    		
        		ObjectMapper mapper = new ObjectMapper();    		
        		s.tryEmitNext(mapper.writeValueAsString(response));
        	}
		}    	
    }
    private String handleMessage(String msg,Sinks.Many<String> pub) {
    	String response = "";
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		JsonNode rootNode = mapper.readTree(msg);
    		int action = rootNode.get("action").asInt();    		
    		if(action == 0) {
    			JsonNode content = rootNode.get("content");    			
    			String userId = content.get("userId").asText();
    			synchronized (sessions) {
    				sessions.put(userId, pub);
				}        		
        		GenericResponseDTO res = new GenericResponseDTO(0,"SUCCESS "+sessions.size());
        		response = mapper.writeValueAsString(res);        		
    		}
    		
    	}
    	catch (Exception e) {
			logger.error(e.getMessage());
		}
    	return response;
    }    
}