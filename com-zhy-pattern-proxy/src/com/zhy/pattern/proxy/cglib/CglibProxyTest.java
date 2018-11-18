package com.zhy.pattern.proxy.cglib;

public class CglibProxyTest  {
    public static void main(String[] args) {
        ZhangSan zhangSan = (ZhangSan)new CglibProxy().getIntance(ZhangSan.class);
        zhangSan.findLove();
    }
}
