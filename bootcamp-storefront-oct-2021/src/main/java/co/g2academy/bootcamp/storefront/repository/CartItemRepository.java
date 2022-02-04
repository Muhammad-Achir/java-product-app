/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.g2academy.bootcamp.storefront.repository;

import co.g2academy.bootcamp.storefront.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author King Engine
 */
public interface CartItemRepository extends CrudRepository<CartItem, Integer>{
    
}
