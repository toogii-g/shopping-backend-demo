package com.shop.application;

import com.shop.application.domain.order.*;
import com.shop.application.domain.products.products.Product;
import com.shop.application.domain.shopping.ShoppingCart;
import com.shop.application.service.OrderService;
import com.shop.application.service.ProductService;
import com.shop.application.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingService shoppingService;
    @Autowired
    OrderService orderService;

    @Override
    public void run(String... args) throws Exception {

        productService.addProduct(123, "Product1", 25.50);
        productService.addProduct(124, "Product2", 20.50);
        productService.addProduct(125, "Product3", 13.75);

        Product product1 = productService.getProduct(123);
        Product product2 = productService.getProduct(124);
        shoppingService.createCart(1);
        shoppingService.addToCart(1, product1, 4);
        shoppingService.addToCart(1, product2, 2);
        shoppingService.changeQuantity(1, 124, 6);

        ShoppingCart cart = shoppingService.getCart(1);
        System.out.println(cart);

        orderService.createOrder(cart, 1);

        Order order = orderService.getOrder(1);
        order.setCustomer(new Customer("Frank Brown", "fbrown@acme.com", "465-234-9080"));
        order.setBillingAddress(new Address("Mainstreet 1", "Fairfield", "52556"));
        order.setShippingAddress(new Address("Mainstreet 34", "Fairfield", "52556"));
        Payment payment = new Payment(300.00, "12/07/2023");
        payment.setPaymentType(new CreditCardPayment("1289 7654 7432", "08/26", "VISA"));
        order.setPayment(payment);
        orderService.saveOrder(order);

        System.out.println(order);

        orderService.placeOrder(1);
    }
}
