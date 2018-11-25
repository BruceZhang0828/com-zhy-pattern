package com.zhy.pattern.templateMethod02;

public class Coffee {
    /**
     * 咖啡充泡方法
     */
    void prepareRecipe(){
        //1.把水煮沸
        boilWater();
        //2.冲泡咖啡
        brewCoffeeGrinds();
        //3.倒进被子
        pourInCup();
        //4.添加糖和牛奶
        addSugarAndMilk();
    }

    //把水 煮沸
    public void boilWater() {
        System.out.println("Boiling water");
    }
    //冲泡 咖啡
    public void brewCoffeeGrinds() {
        System.out.println("Dripping Coffee through filter");
    }
    //把咖啡 倒进杯子
    public void pourInCup(){
        System.out.println("Pouring into cup");
    }

    public void addSugarAndMilk(){
        System.out.println("Adding Sugar And Milk");
    }

}
