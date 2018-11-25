package com.zhy.pattern.templateMethod02;

public class Tea {
    //泡茶的方法
    void prepareRecipe(){
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    public void boilWater() {
        System.out.println("Boiling Water");
    }

    public void steepTeaBag() {
        System.out.println("Steeping the tea");
    }

    public void pourInCup() {
        System.out.println("pouring into cup");
    }

    public void addLemon() {
        System.out.println("Adding Lemon");
    }


}
