package cn.pipipan.eisproject.websocketserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{traderId}")
public class WebSocket {
    Logger logger = LoggerFactory.getLogger(WebSocket.class);
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<>();
    private String traderId;
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("traderId") String traderId, Session session){
        logger.info("connect {}", traderId);
        this.traderId = traderId;
        this.session = session;
        clients.put(traderId, this);
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println(error.getMessage());
    }

    @OnClose
    public void onClose(){
        clients.remove(traderId);
    }

    public static void sendMessage(String message) throws IOException{
        for (WebSocket item : clients.values()){
            item.session.getAsyncRemote().sendText(message);
        }
    }
}
