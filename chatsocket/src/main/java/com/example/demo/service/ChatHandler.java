package com.example.demo.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatHandler extends TextWebSocketHandler{

	private final List<String> messages = null;
	private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		sessions.add(session);
		
		
		}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		sessions.remove(session);
	}
	
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String receivedMessage = message.getPayload();
		broadCastMessage(receivedMessage);
		String keyword="websockect";
		if(receivedMessage.toLowerCase().contains(keyword)) {
			
			String winnerMessage = "WebSocket es un protocolo que genera una comunicacion duplex entre cliente y servidor, en cambio que http en el cual el cliente tiene que hacer una peticion en Web Socket el servidor puede enviar datos sin que el cliente haya hecho un peticion. Ademas ambos trabajan en los mismos puertos los cuales son el puerto 80 y el puerto 443";
	        broadCastMessage(winnerMessage);
		}
	}
	public void broadCastMessage(String message)throws Exception {
		for(WebSocketSession s:sessions) {
			if(s.isOpen()) {
				s.sendMessage(new TextMessage(message));
				
			}
		}
	}}