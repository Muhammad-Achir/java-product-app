/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.orderfulfillment.controller;

import co.g2academy.bootcamp.orderfulfillment.entity.Order;
import co.g2academy.bootcamp.orderfulfillment.entity.OrderItem;
import co.g2academy.bootcamp.orderfulfillment.kafka.KafkaListenerCallback;
import co.g2academy.bootcamp.orderfulfillment.kafka.KafkaOrderFulfillmentTopicConfig;
import co.g2academy.bootcamp.orderfulfillment.repository.OrderRepository;
import co.g2academy.bootcamp.storefront.model.OrderModel;
import co.g2academy.bootcamp.storefront.model.OrderStatus;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author King Engine
 */
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private KafkaTemplate<String, OrderStatus> kafkaTemplate;
    
    private KafkaListenerCallback calllback = new KafkaListenerCallback();

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            for (OrderItem orderItem : order.getOrderItem()) {
                orderItem.setOrder(null);
            }
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateOrderString(@PathVariable Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus("DELIVERED");
            orderRepository.save(order);
            
            //send the order status back to store front so it also 
//            update cart status to delivered
            sendMessage(convertOrderToOrderStatus(order));
        }
        return ResponseEntity.ok("ok");
    }
    
    public void sendMessage(OrderStatus order){
        //implement kafka send message
//        OrderStatus orderStatus = convertOrderToOrderStatus(order);
        //send to kafka using kafka template
        ListenableFuture<SendResult<String, OrderStatus>> future
                = kafkaTemplate.send(
                        KafkaOrderFulfillmentTopicConfig.ORDER_FULFILLMENT_KAFKA_TOPIC, order);
        future.addCallback(calllback);
    }
    
    public OrderStatus convertOrderToOrderStatus(Order order){
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setCartId(order.getCartId());
        orderStatus.setOrderId(order.getId());
        orderStatus.setStatus(order.getStatus());
        return orderStatus;
    }
}
