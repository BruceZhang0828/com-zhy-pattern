package com.zhy.pattern.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class JdkProxyTest {

    public static void main(String[] args) {
        Person me = (Person)new JdkProxy().getInstance(new Me());
        me.findLove();
        me.findJob();
        System.out.println(me.getClass());
        /**
         * 字节码重组的原理:
         * 1.拿到被代理对象的引用;并且获取它的所有接口
         * 2.JDK Proxy类重新生成一个新类,同时新的类要实现被代理类所有实现的接口
         * 3.动态生成java代码,新加的业务逻辑由一定的业务逻辑去调用(在代码中体现)
         * 4.编译新生成的java代码.class
         * 5.再重新加载jvm中运行
         * 是$开头的类都是自动生成的
         */
        try {
            /**
             * 通过反编译工具 反编译为字节码文件:$Proxy0.class
             */
            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
            FileOutputStream fos = new FileOutputStream("$Proxy0.class");
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
