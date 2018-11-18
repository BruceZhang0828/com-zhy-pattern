package com.zhy.pattern.proxy.staticed;

public class Father {
    private Son son;
    public Father(Son son){
        this.son = son;
    }

    public void  findLove(){
        System.out.println("择偶条件");
        this.son.findLove();
        System.out.println("有没有合适的给介绍一下");
    }
}
