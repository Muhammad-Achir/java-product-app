/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.bootcamp.storefront.security;

import co.g2academy.bootcamp.storefront.entity.Person;
import co.g2academy.bootcamp.storefront.repository.PersonRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author King Engine
 */

@Service
public class JwtUserDetailService implements UserDetailsService{
    
    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Person person = personRepository.findByName(userName);
        if (person != null) {
            return new User(userName, person.getPassword(), new ArrayList<>());
            
        }
        throw new UsernameNotFoundException("User" + userName + " not found");
    }
}
