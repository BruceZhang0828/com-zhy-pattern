package com.zhy.pattern.strategy;

import com.zhy.pattern.strategy.Order;
import com.zhy.pattern.strategy.PayState;
import com.zhy.pattern.strategy.payport.AliPay;
import com.zhy.pattern.strategy.payport.PayType;

public class PayStrategyTest {

    public static void main(String[] args) {
        Order order = new Order("1","20181124",10.0);
        /*
         * 开始支付,选择微信支付 .支付宝,银联卡,京东白条,财付通
         * 每个渠道具体的支付方式是不一样的
         * 基本算法是固定的
         * */
        PayState payState = order.pay(PayType.WEICHANT_PAY);

        System.out.println(payState);
    }
}
