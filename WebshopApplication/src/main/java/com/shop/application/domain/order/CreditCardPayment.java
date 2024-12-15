package com.shop.application.domain.order;

public class CreditCardPayment implements IPaymentType{
    private String creditCardNumber;
    private String validationDate;
    private String creditCardType;

    public CreditCardPayment(String creditCardNumber, String validationDate, String creditCardType) {
        this.creditCardNumber = creditCardNumber;
        this.validationDate = validationDate;
        this.creditCardType = creditCardType;
    }



    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", validationDate='" + validationDate + '\'' +
                ", creditCardType='" + creditCardType + '\'' +
                '}';
    }

    @Override
    public String getCardType() {
        return creditCardType;
    }
}
