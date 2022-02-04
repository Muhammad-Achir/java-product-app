/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.orderfulfillment.repository;

import co.g2academy.bootcamp.orderfulfillment.entity.Order;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author King Engine
 */
public interface OrderRepository extends CrudRepository<Order, Integer>{
    
}
