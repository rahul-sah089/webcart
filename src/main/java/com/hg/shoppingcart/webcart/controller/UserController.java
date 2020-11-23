package com.hg.shoppingcart.webcart.controller;

import com.hg.shoppingcart.webcart.model.Users;
import com.hg.shoppingcart.webcart.model.Users;
import com.hg.shoppingcart.webcart.repositories.UserRepository;
import com.hg.shoppingcart.webcart.services.UserService;
import com.hg.shoppingcart.webcart.utils.HeaderUtils;
import com.hg.shoppingcart.webcart.validators.UsersValidators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signUp(@RequestBody Users user) {
        log.debug("Rest request to sign up new users : {}", user);
        ResponseEntity responseEntity = UsersValidators.validateUsersDTO(user);
        if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return responseEntity;
        }
        if (userService.getUserByUserName(user.getUsername()) != null) {
            return ResponseEntity.badRequest().headers(HeaderUtils.createFailureAlert("users", "Username already exist", "Username already exist")).body(null);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Users users = userRepository.save(user);
        return ResponseEntity.ok(users);
    }
}
