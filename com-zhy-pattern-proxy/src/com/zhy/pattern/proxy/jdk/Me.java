package com.zhy.pattern.proxy.jdk;

public class Me implements Person{

    @Override
    public void findLove() {
        System.out.println("找对象...");
    }

    @Override
    public void findJob() {
        System.out.println("找工作...");
    }

    @Override
    public void findHouse() {
        System.out.println("找房子...");
    }
}
