package com.shop.application;

import com.shop.application.domain.order.*;
import com.shop.application.domain.products.products.Product;
import com.shop.application.domain.shopping.ShoppingCart;
import com.shop.application.service.OrderService;
import com.shop.application.service.ProductService;
import com.shop.application.service.ShoppingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WebshopApplicationTests {

	@Mock
	private ProductService productService;

	@Mock
	private ShoppingService shoppingService;

	@Mock
	private OrderService orderService;

	@InjectMocks
	private Application application;

	private Product product1;
	private Product product2;
	private ShoppingCart cart;
	private Order order;

	@BeforeEach
	public void setUp() {
		product1 = new Product(123, "Product1", 25.50);
		product2 = new Product(124, "Product2", 20.50);

		cart = new ShoppingCart(1);
		cart.addToCart(product1, 4);
		cart.addToCart(product2, 2);

		order = new Order(1);
	}

	@Test
	public void testRun() throws Exception {
		when(productService.getProduct(123)).thenReturn(product1);
		when(productService.getProduct(124)).thenReturn(product2);

		doNothing().when(shoppingService).createCart(1);
		doNothing().when(shoppingService).addToCart(1, product1, 4);
		doNothing().when(shoppingService).addToCart(1, product2, 2);
		doNothing().when(shoppingService).changeQuantity(1, 124, 6);
		when(shoppingService.getCart(1)).thenReturn(cart);

		doNothing().when(orderService).createOrder(cart, 1);
		when(orderService.getOrder(1)).thenReturn(order);
		doNothing().when(orderService).saveOrder(any(Order.class));
		doNothing().when(orderService).placeOrder(1);

		application.run();

		verify(productService, times(3)).addProduct(anyInt(), anyString(), anyDouble());
		verify(productService, times(2)).getProduct(anyInt());

		verify(shoppingService).createCart(1);
		verify(shoppingService).addToCart(1, product1, 4);
		verify(shoppingService).addToCart(1, product2, 2);
		verify(shoppingService).changeQuantity(1, 124, 6);
		verify(shoppingService).getCart(1);

		verify(orderService).createOrder(cart, 1);
		verify(orderService).getOrder(1);
		verify(orderService).saveOrder(any(Order.class));
		verify(orderService).placeOrder(1);
	}

	@Test
	public void testOrderDetails() {
		order.setCustomer(new Customer("Frank Brown", "fbrown@acme.com", "465-234-9080"));
		order.setBillingAddress(new Address("Mainstreet 1", "Fairfield", "52556"));
		order.setShippingAddress(new Address("Mainstreet 34", "Fairfield", "52556"));
		Payment payment = new Payment(300.00, "12/07/2023");
		payment.setPaymentType(new CreditCardPayment("1289 7654 7432", "08/26", "VISA"));
		order.setPayment(payment);

		assertAll(
				() -> assertEquals("Frank Brown", order.getCustomer().getName()),
				() -> assertEquals("Mainstreet 1", order.getBillingAddress().getStreet()),
				() -> assertEquals("VISA", ((CreditCardPayment) payment.getPaymentType()).getCardType())
		);
	}

}
