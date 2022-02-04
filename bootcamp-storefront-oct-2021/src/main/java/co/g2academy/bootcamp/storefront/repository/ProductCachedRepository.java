/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.storefront.repository;

import co.g2academy.bootcamp.storefront.entity.Person;
import co.g2academy.bootcamp.storefront.entity.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 *
 * @author King Engine
 */
@Component
public class ProductCachedRepository {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Cacheable(value = "findByPerson", key = "#person.id")
    public List<Product> findByPerson(Person person){
        return productRepository.findByPerson(person);
    }
    
    @Cacheable(value = "findByIdAndPerson", key = "#id + '-' + #person.id")
    public Product findByIdAndPerson(Integer id, Person person){
        return productRepository.findByIdAndPerson(id, person);
    }
    
//    @Cacheable(value = "findByProductContaining", key = "#product")
//    public List<Product> findByProductContaining(Product product){
//        return productRepository.findByProductContaining(product);
//    }
}
