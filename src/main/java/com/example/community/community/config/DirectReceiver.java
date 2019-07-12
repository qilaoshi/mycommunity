package com.example.community.community.config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
    @RabbitListener(queues = "queue-one")
    public void handler(String msg){
        System.out.println("消息");
        System.out.println("DirectRecevier:"+msg);
        System.out.println();
    }
    @RabbitListener(queues = "123")
    public void handlerThree(String msg){
        System.out.println("消息3");
        System.out.println("DirectRecevier:"+msg);
        System.out.println();
    }

    @RabbitListener(queues ="queue-two")
    public void handlerTwo(String msg){
        System.out.println("消息2");
        System.out.println("DirectRecevier:"+msg);
        System.out.println();
    }
}
