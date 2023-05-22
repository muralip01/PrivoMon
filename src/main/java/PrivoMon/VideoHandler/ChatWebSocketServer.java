package PrivoMon.VideoHandler;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/chat/{roomId}/{userId}")
@Component
public class ChatWebSocketServer {

    private static Map<Integer, Map<Integer, Session>> roomSessions = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(ChatWebSocketServer.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") Integer roomId, @PathParam("userId") Integer userId) {
        logger.info("User with id: " + userId + " entered room with id: " + roomId);

        roomSessions.computeIfAbsent(roomId, k -> new Hashtable<>()).put(userId, session);
        broadcastMessage("User with id: " + userId + " entered the room", roomId);
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("roomId") Integer roomId, @PathParam("userId") Integer userId) {
        logger.info("Received Message: " + message);
        broadcastMessage(userId + ": " + message, roomId);
    }

    @OnClose
    public void onClose(Session session, @PathParam("roomId") Integer roomId, @PathParam("userId") Integer userId) {
        logger.info("User with id: " + userId + " left room with id: " + roomId);

        Map<Integer, Session> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(userId);
        }
        broadcastMessage("User with id: " + userId + " left the room", roomId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("Error occurred in WebSocket server", throwable);
    }

    private void broadcastMessage(String message, Integer roomId) {
        Map<Integer, Session> sessions = roomSessions.get(roomId);

        if (sessions != null) {
            sessions.forEach((userId, session) -> {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error("Error sending message", e);
                }
            });
        }
    }
}