package com.example.community.community.test;

import com.example.community.community.Service.PublishService;
import com.example.community.community.config.RabbitFanoutConfig;
import com.example.community.community.mapper.NotificationMapper;
import com.example.community.community.mapper.UserMapper;
import com.example.community.community.model.Notification;
import com.example.community.community.model.User;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishService publishService;
    @Autowired
    private NotificationMapper notificationMapper;
    @Test
    public void test(){
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT,"","要的啊啊");
    }

    @Test
    public void sendmsg(){
        System.out.println("第二个消息");
        rabbitTemplate.convertAndSend("user-fanout",null,"可以ddd啊");

    }
    @Test
    public void a(){
        System.out.println("pyd size is" +publishService.select().size());
    }

    @Test
    public void tests() {


    }


}
@Data
class  Hero{
    private String heroName;
    public float hp;
    public int damage;
    public Hero(String heroName,float hp,int dmage){
        this.heroName=heroName;
        this.damage=dmage;
        this.hp=hp;
    }
    public String toString(){
        return heroName;
    }
}


