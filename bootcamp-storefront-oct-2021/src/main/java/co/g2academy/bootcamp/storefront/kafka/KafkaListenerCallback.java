/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.storefront.kafka;

import co.g2academy.bootcamp.storefront.model.OrderModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 *
 * @author King Engine
 */
public class KafkaListenerCallback implements ListenableFutureCallback<SendResult <String, OrderModel>>{

    @Override
    public void onSuccess(SendResult <String, OrderModel> result) {
        System.out.println("Message sent with result offset " + 
                result.getRecordMetadata().offset());
    }

    @Override
    public void onFailure(Throwable ex) {
        System.out.println("Unable to send message with cause " + 
                ex.getMessage());
    }
    
}
