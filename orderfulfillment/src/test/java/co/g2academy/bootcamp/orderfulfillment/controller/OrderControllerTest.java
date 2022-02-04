/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.g2academy.bootcamp.orderfulfillment.controller;

import co.g2academy.bootcamp.orderfulfillment.entity.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author King Engine
 */
public class OrderControllerTest {
    
    private OrderController orderController = new OrderController();

    @Test
    public void testGetOrder() {
        Order order = new Order();
        order.setCartId(1);
        order.setId(2);
        order.setStatus("ACTIVE");
        
        ResponseEntity<Order> result = orderController.getOrder(order.getCartId());
        assertEquals(order, result);
    }

    @Test
    public void testUpdateOrderString() {
    }

    @Test
    public void testConvertOrderToOrderStatus() {
    }
    
}
