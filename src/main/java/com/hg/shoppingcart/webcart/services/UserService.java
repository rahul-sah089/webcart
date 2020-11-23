package com.hg.shoppingcart.webcart.services;

import com.hg.shoppingcart.webcart.model.Users;
import com.hg.shoppingcart.webcart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Users getUserByUserName(String username){
        return userRepository.findByUsername(username);
    }
}
