package com.zhy.pattern.strategy.payport;

import com.zhy.pattern.strategy.PayState;

public class JdPay implements Payment {
    @Override
    public PayState pay(String uid, double amount) {
        System.out.println("欢迎使用京东白条");

        System.out.println("扣除手续费");
        PayState payState = new PayState(200, amount, "支付成功 支付金额为:" + amount);
        return payState;
    }
}
