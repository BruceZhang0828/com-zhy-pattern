package com.zhy.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 1.被代理的对象必须实现一个接口
 * 2.代理对象实现InvocationHandler接口
 * 3.Proxy.newProxyInstance()方法生成代理对象
 * 4.在invoke()方法中进行功能的增强
 */
public class JdkProxy implements InvocationHandler {
    private Person target;

    public Object getInstance(Person target){
        this.target = target;
        Class<? extends Person> clazz = target.getClass();
        //用来新生成一个对象 (字节码重组来生成)
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里进行 工能的改进增强

        if("findLove".equals(method.getName())){
            System.out.println("我是媒婆：我要给你找对象，现在已经拿到你的需求");
            System.out.println("开始物色");
            method.invoke(this.target,args);
            System.out.println("如果合适的话，就准备办事");
        }else if("findJob".equals(method.getName())){
            System.out.println("我是58：我要给你找工作，现在已经拿到你的简历");
            System.out.println("开始投递");
            method.invoke(this.target,args);
            System.out.println("安排面试");
        }else {
            method.invoke(this.target,args);
        }

        return null;
    }
}
