package com.zhy.pattern.proxy.staticed;

/**
 * 静态代理 扩展难;因为如何对son对象进行修改 father的内容也需要进行修改
 */
public class StaticedProxyTest {

    public static void main(String[] args) {
        Son son = new Son();
        Father father = new Father(son);
        father.findLove();
    }
}
