/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.storefront.controller;

import co.g2academy.bootcamp.storefront.entity.Person;
import co.g2academy.bootcamp.storefront.repository.PersonRepository;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author King Engine
 */

@RestController
@RequestMapping("/api")
public class PersonController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @PutMapping("/person/{id}")
    private ResponseEntity<String> update(@RequestBody Person person,
            @PathVariable Integer id, Principal principal) {
        Person personToUpdate = personRepository.findByIdAndName(id, principal.getName());
        if (personToUpdate != null){
//            Person personToUpdate = personToUpdateOptional.get();
            personToUpdate.setPassword(person.getPassword());
            personRepository.save(personToUpdate);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.ok("usert not found");
    }
    
    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id, Principal principal) {
        Person personToDelete = personRepository.findByIdAndName(id, principal.getName());
        if (personToDelete != null) {
            personRepository.deleteById(id);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.ok("user not found");
    }
    
    @DeleteMapping("/person/")
    public ResponseEntity<String> deleteAll(){
        personRepository.deleteAll();
        return ResponseEntity.ok("ok");
    }
}
