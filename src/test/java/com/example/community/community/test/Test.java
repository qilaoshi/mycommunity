package com.example.community.community.test;

import lombok.Data;

public class Test {
    public static void main(String[] args) {
       Hero hero=new Hero();
       hero.hp=5;
       Thread t=new Thread(){
           @Override
           public void run() {
               while(true){
                   hero.attack();
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       };
       t.start();
        Thread t2=new Thread(){
            @Override
            public void run() {
                while(true) {
                    hero.recover();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();
    }

}
@Data
class Hero{
    private String name;
    public int hp;
    private int damage;
    public synchronized void recover(){
        while(hp==1) {
            hp = hp + 1;
            System.out.println("回血hp is" + hp);
        }
        while (hp==5) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notify();
        }
    }
    public synchronized void attack(){
        if (hp==1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            hp=hp-1;
            System.out.println("攻击后hp是"+hp);
    }
}
