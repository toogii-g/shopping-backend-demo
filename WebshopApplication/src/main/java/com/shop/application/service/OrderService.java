package com.shop.application.service;

import com.shop.application.domain.order.Order;
import com.shop.application.dao.OrderDAO;
import com.shop.application.domain.order.Orderline;
import com.shop.application.domain.products.products.Product;
import com.shop.application.domain.shopping.Cartitem;
import com.shop.application.domain.shopping.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderDAO orderDAO = new OrderDAO();
    EmailSender emailSender = new EmailSender();


    public void createOrder(ShoppingCart shoppingCart, int ordernumber){
        Order order = new Order(ordernumber);
        for (Cartitem cartitem: shoppingCart.getCartlist()){
            order.addOrderLine(new Orderline(cartitem.getProduct(), cartitem.getQuantity()));
        }
        orderDAO.save(order);

        for(Orderline orderline:order.getOrderlines()){
            Product product = orderline.getProduct();
        }
    }

    public Order getOrder(int ordernumber){
        return orderDAO.find(ordernumber);
    }

    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    public void placeOrder(int ordernumber) {
        Order order = getOrder(ordernumber);
        emailSender.sendEmail(order.getCustomer().getEmail(), "Thank for your order with ordernumber "+order.getOrdernumber());
    }
}
