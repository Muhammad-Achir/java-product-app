/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.g2academy.bootcamp.storefront.repository;

import co.g2academy.bootcamp.storefront.entity.Person;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author King Engine
 */
public interface PersonRepository extends CrudRepository<Person, Integer>{
    
    public Person findByName(String userName);
    
    public Person findByIdAndName(Integer id, String userName);
    
}
