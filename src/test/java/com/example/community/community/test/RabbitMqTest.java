package com.example.community.community.test;

import com.example.community.community.config.RabbitFanoutConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void test(){
        System.out.println("要的");
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT,"","要的啊啊");
    }

    @Test
    public void sendmsg(){
        System.out.println("第二个消息");
        rabbitTemplate.convertAndSend("user-fanout",null,"可以啊");

    }
    @Test
    public void tests() {
        ArrayList<Hero> arrayList = new ArrayList<>();
        for (int i=0;i<=5;i++){
            arrayList.add(new Hero("hero"+i));
        }
        Iterator<Hero> it=arrayList.iterator();
        while (it.hasNext()){
            Hero hero=it.next();
            System.out.println(hero);
        }
    }

}
class  Hero{
    private String heroName;
    public Hero(String heroName){
        this.heroName=heroName;
    }
    public String toString(){
        return heroName;
    }
}
