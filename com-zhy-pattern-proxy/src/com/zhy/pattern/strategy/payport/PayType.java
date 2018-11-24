package com.zhy.pattern.strategy.payport;

public enum PayType {
    ALI_PAY(new AliPay()),
    WEICHANT_PAY(new WeChantPay()),
    JD_PAY(new JdPay()),
    UNION_PAY(new UnionPay());

    private Payment payment;
    PayType(Payment payment){
        this.payment = payment;
    }

    public Payment getPayment() {
        return this.payment;
    }
}
