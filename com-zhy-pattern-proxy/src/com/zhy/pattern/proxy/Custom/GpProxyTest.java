package com.zhy.pattern.proxy.Custom;

import com.zhy.pattern.proxy.jdk.JdkProxy;
import com.zhy.pattern.proxy.jdk.Me;
import com.zhy.pattern.proxy.jdk.Person;

public class GpProxyTest {
    public static void main(String[] args) {
        Person me = (Person)new CustomProxy().getInstance(new Me());
        me.findLove();
        me.findJob();
        System.out.println(me.getClass());
    }
}
