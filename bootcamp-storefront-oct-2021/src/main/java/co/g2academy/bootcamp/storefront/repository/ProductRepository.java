/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.g2academy.bootcamp.storefront.repository;

import co.g2academy.bootcamp.storefront.entity.Person;
import co.g2academy.bootcamp.storefront.entity.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author King Engine
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
        
    public List<Product> findByPerson (Person person);
    
    public Product findByIdAndPerson(Integer id, Person person);
    
    public List<Product> findByNameContains(String name, Pageable pageable);

    public List<Product> findByCategory(String category, Pageable pageable);
}
