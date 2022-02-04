/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.orderfulfillment.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 *
 * @author King Engine
 */
@Configuration
public class KafkaOrderFulfillmentTopicConfig {
    
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    
//    public static final String ORDER_FULFILLMENT_KAFKA_TOPIC = "order-fulfillment";
    public static final String ORDER_FULFILLMENT_KAFKA_TOPIC = "order-fulfillment-delivered";
    
    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic orderFulFillmentTopic(){
        return new NewTopic(ORDER_FULFILLMENT_KAFKA_TOPIC, 1, (short) 1);
    }
}
