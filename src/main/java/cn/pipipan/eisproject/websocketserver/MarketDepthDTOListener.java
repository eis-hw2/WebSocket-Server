package cn.pipipan.eisproject.websocketserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "marketDepth")
public class MarketDepthDTOListener {
    Logger logger = LoggerFactory.getLogger(MarketDepthDTOListener.class);
    @RabbitHandler
    public void handle(String message){
        logger.info("marketDetph: {}", message);
        try {
            WebSocket.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
