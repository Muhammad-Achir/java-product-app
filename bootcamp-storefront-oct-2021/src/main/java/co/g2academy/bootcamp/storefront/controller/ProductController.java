/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.storefront.controller;

import co.g2academy.bootcamp.storefront.entity.Person;
import co.g2academy.bootcamp.storefront.entity.Product;
import co.g2academy.bootcamp.storefront.repository.PersonRepository;
import co.g2academy.bootcamp.storefront.repository.ProductCachedRepository;
import co.g2academy.bootcamp.storefront.repository.ProductRepository;
import java.awt.print.Pageable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author King Engine
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ProductController {

    // collection as data base
    // private List<Product> products = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PersonRepository personRepository;

    // redis
    @Autowired
    private ProductCachedRepository productCachedRepository;

//    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/product/")
    public ResponseEntity<List<Product>> getProducts(Principal principal) {

//        Product iphone = new Product();
//        iphone.setId(1);
//        iphone.setName("iphone white 256gb");
//        iphone.setStock(10);
//        iphone.setPrice(10_000_000);
//        iphone.setCategory("Handphone and smartphone");
//
//        Product android = new Product();
//        iphone.setId(2);
//        iphone.setName("android samsung galaxy white 256gb");
//        iphone.setStock(19);
//        iphone.setPrice(7_000_000);
//        iphone.setCategory("Handphone and smartphone");
//
//        List<Product> products = new ArrayList<>();
//        products.add(iphone);
//        products.add(android);
//connected in data base
        Person person = personRepository.findByName(principal.getName());
//        Iterable<Product> products = productRepository.findAll();
//        List<Product> productList = new ArrayList<>();

//mot use redis
        List<Product> products = productRepository.findByPerson(person);
//        use redis
//        List<Product> products = productCachedRepository.findByPerson(person);

        // make person null
        for (Product product : products) {
            product.setPerson(null);
        }

//        for (Product product : products) {
//            productList.add(product);
//        }
        return ResponseEntity.ok(products);

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable("id") Integer id, Principal principal) {
//        Product iphone = new Product();
//        iphone.setId(id);
//        iphone.setName("iphone white 256gb");
//        iphone.setStock(10);
//        iphone.setPrice(10_000_000);
//        iphone.setCategory("Handphone and smartphone");

//collection
//        for (Product product : products) {
//            if (product.getId().equals(id)) {
//                return ResponseEntity.ok(product);
//            }
//        }
//connected in data base
        Person person = personRepository.findByName(principal.getName());
//        Optional<Product> productOptional = productRepository.findById(id);

// not uer redis
//        Product product = productRepository.findByIdAndPerson(id, person);
//        use redis
        Product product = productCachedRepository.findByIdAndPerson(id, person);

        // make person null
        product.setPerson(null);
//        if (productOptional.isPresent()) {
//            return ResponseEntity.ok(productOptional.get());
//        }
        return ResponseEntity.ok(product);
    }

//    @GetMapping("/search/{q}")
//    public ResponseEntity<List <Product>> findByProductContaining(
//    @PathVariable("q") Integer id, Principal principal) {
//        Product product = productRepository.findByProduct(principal.getName());
//        List<Product> products = productCachedRepository.findByProductContaining(product);
//        
//        Pageable firstPageWithTwoElements = (Pageable) PageRequest.of(0, 2);
//        
//        Page<Product> allProducts = productRepository.findAll(firstPageWithTwoElements);
//        
//        Pageable sortedByName = (Pageable) PageRequest.of(0, 0, Sort.by("name"));
//        
//        return ResponseEntity.ok(products);
//    }

    @PostMapping("/product/")
    public ResponseEntity<String> save(@RequestBody Product product, Principal principal) {
        //get maximum id in product list
        //collection
//        if (products.isEmpty()) {
//            product.setId(1);
//        } else {
//            Product lastProduct = products.get(products.size() - 1);
//            Integer maxId = lastProduct.getId();
//            Integer newId = maxId + 1;
//            product.setId(newId);
//        }
//        products.add(product);

//connected in data base
        Person person = personRepository.findByName(principal.getName());
        product.setPerson(person);
        productRepository.save(product);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> update(@RequestBody Product product,
            @PathVariable Integer id, Principal principal) {
        //collection
//        for (Product existingProduct : products) {
//            if (existingProduct.getId().equals(id)) {
//                existingProduct.setName(product.getName());
//                existingProduct.setStock(product.getStock());
//                existingProduct.setPrice(product.getPrice());
//                existingProduct.setCategory(product.getCategory());
//                return ResponseEntity.ok("ok");
//            }
//        }

//connected in data base
        Person person = personRepository.findByName(principal.getName());
        Product productToUpdate = productRepository.findByIdAndPerson(id, person);
        if (productToUpdate != null) {
//            Product productToUpdate = productToUpdateOptional.get();
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setName(product.getName());
            productToUpdate.setStock(product.getStock());
            productToUpdate.setPrice(product.getPrice());
            productRepository.save(productToUpdate);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.ok("Product not found");
    }
    
    @PutMapping("/product/stock/{id}")
    public ResponseEntity<String> updateStock (@RequestBody Product product,
            @PathVariable Integer id, Principal principal) {
        Person person = personRepository.findByName(principal.getName());
        Product productUpdateStock = productRepository.findByIdAndPerson(id, person);
        if (productUpdateStock != null) {
            productUpdateStock.setStock(product.getStock());
            productRepository.save(productUpdateStock);
            return ResponseEntity.ok("ok");
        }
        
        return ResponseEntity.ok("Product not found");
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id, Principal principal) {
        //collection
//        Product deleteProduct = null;
//        for (Product product : products) {
//            if (product.getId().equals(id)) {
//                deleteProduct = product;
//                break;
//            }
//        }
//        if (deleteProduct != null) {
//            products.remove(deleteProduct);
//        return ResponseEntity.ok("ok");
//        }

//connected to data base
        Person person = personRepository.findByName(principal.getName());
        Product productToDelete = productRepository.findByIdAndPerson(id, person);
        if (productToDelete != null) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.ok("Product not found");
    }

    @DeleteMapping("/product/")
    public ResponseEntity<String> deleteAll(Principal principal) {
        //collection
//        products.clear();

//connected to data base
        Person person = personRepository.findByName(principal.getName());
        List<Product> products = productRepository.findByPerson(person);
        for (Product product : products) {
            productRepository.delete(product);
        }
        return ResponseEntity.ok("ok");
    }

}
