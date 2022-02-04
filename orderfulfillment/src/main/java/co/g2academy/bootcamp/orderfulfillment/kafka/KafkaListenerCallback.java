/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.orderfulfillment.kafka;

import co.g2academy.bootcamp.orderfulfillment.entity.Order;
import co.g2academy.bootcamp.storefront.model.OrderModel;
import co.g2academy.bootcamp.storefront.model.OrderStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 *
 * @author King Engine
 */
public class KafkaListenerCallback implements ListenableFutureCallback<SendResult <String, OrderStatus>>{

    @Override
    public void onSuccess(SendResult <String, OrderStatus> result) {
        System.out.println("Message sent with result offset " + 
                result.getRecordMetadata().offset());
    }

    @Override
    public void onFailure(Throwable ex) {
        System.out.println("Unable to send message with cause " + 
                ex.getMessage());
    }
    
}
