package com.shop.application.domain.order;

public class Payment {
    private double amount;
    private String date;
    private IPaymentType paymentType;

    public Payment(double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public void setPaymentType(IPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public IPaymentType getPaymentType() {
        return paymentType;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", date='" + date + '\'' +
                ", paymentType=" + paymentType +
                '}';
    }
}
