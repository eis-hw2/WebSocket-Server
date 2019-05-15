package cn.pipipan.eisproject.websocketserver;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebSocketServerApplication {
    @Bean
    Queue queue(){
        return new Queue("marketDepth");
    }

    @Bean
    Exchange exchange(){
        return new DirectExchange("marketDepth");
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with("marketDepth").noargs();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebSocketServerApplication.class, args);
    }

}
