/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.storefront.kafka;

import co.g2academy.bootcamp.storefront.entity.Cart;
import co.g2academy.bootcamp.storefront.entity.CartItem;
import co.g2academy.bootcamp.storefront.model.OrderItemModel;
import co.g2academy.bootcamp.storefront.model.OrderModel;
import co.g2academy.bootcamp.storefront.model.OrderStatus;
import co.g2academy.bootcamp.storefront.repository.CartItemRepository;
import co.g2academy.bootcamp.storefront.repository.CartRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author King Engine
 */
@Component
public class KafkaOrderFulfillmentListener {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @KafkaListener(topics = "order-fulfillment-delivered", groupId = "foo",
            containerFactory = "kafkaListenerFactory")
    public void listenOrderFulfillmentTopic(@Payload OrderStatus orderStatus) {
        System.out.println("Update processed to delivered (Status)");

        Iterable<Cart> carts = cartRepository.findAll();
        for (Cart cart : carts) {
            if (orderStatus.getCartId() == cart.getId()) {
                System.out.println(cart.getStatus());
                cart.setStatus(orderStatus.getStatus());
                cartRepository.save(cart);
                System.out.println(cart.getStatus());
            }
        }
//
//        System.out.println(principal.getName());
//        Cart carts = cartRepository.findByCartIdAndOrderId(orderStatus.getCartId(), orderStatus.getOrderId());
//        System.out.println("cart" + carts);
//        System.out.println(carts.getStatus());
//        cartRepository.save(convertOrderModelToOrder(orderStatus));
    }

    public Cart convertOrderModelToOrder(OrderStatus orderStatus) {
        System.out.println(orderStatus.getCartId());
        System.out.println(orderStatus.getClass());
        System.out.println(orderStatus.getOrderId());
        System.out.println(orderStatus.getStatus());
        Cart cart = new Cart();
        cart.setCartItems(null);

        cart.setId(orderStatus.getCartId());
        cart.setPerson(null);
        cart.setStatus(orderStatus.getStatus());
        cart.setTransactionDate(null);

        List<CartItem> cartItems = new ArrayList<>();
//        
//        for (OrderItemModel orderItemModel : orderModel.getOrderItems()) {
//            OrderItem orderItem = new  OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setPrice(orderItemModel.getPrice());
//            orderItem.setProductId(orderItemModel.getProductId());
//            orderItem.setProductName(orderItemModel.getProductName());
//            orderItem.setQuantity(orderItemModel.getQuantity());
//            orderItems.add(orderItem);
//        }
//        order.setOrderItem(orderItems);
        return cart;
    }
}
