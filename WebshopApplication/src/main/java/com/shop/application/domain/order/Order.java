package com.shop.application.domain.order;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Order {
    private List<Orderline> orderlines = new LinkedList<Orderline>();
    private double totalPrice= 0;
    private Customer customer;
    private int ordernumber;
    private Address billingAddress;
    private Address shippingAddress;
    private Payment payment;

    public Order(int ordernumber) {
        this.ordernumber= ordernumber;
    }
    


    public void computeTotalPrice(){
        totalPrice = 0;
        Iterator<Orderline> iter = orderlines.iterator();
        while (iter.hasNext()){
            Orderline cartitem =  iter.next();
            totalPrice = totalPrice + (cartitem.getQuantity()* cartitem.getProduct().getPrice());
        }
    }
    




    public void addOrderLine (Orderline ol){
    	orderlines.add(ol);
    }
    public double getTotalPrice() {
        return totalPrice;
    }

	public List<Orderline> getOrderlines() {
		return orderlines;
	}

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderlines=" + orderlines +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                ", ordernumber=" + ordernumber +
                ", billingAddress=" + billingAddress +
                ", shippingAddress=" + shippingAddress +
                ", payment=" + payment +
                '}';
    }
}
