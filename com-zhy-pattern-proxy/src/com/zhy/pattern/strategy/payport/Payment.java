package com.zhy.pattern.strategy.payport;

import com.zhy.pattern.strategy.PayState;

public interface Payment {
    PayState pay(String uid,double amount);
}

