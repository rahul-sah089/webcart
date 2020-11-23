package com.hg.shoppingcart.webcart.validators;

import com.hg.shoppingcart.webcart.model.Users;
import com.hg.shoppingcart.webcart.services.UserService;
import com.hg.shoppingcart.webcart.utils.HeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UsersValidators {

    @Autowired
    static UserService userService;

    public static ResponseEntity validateUsersDTO(Users user) {
        if (user.getPassword() == null || user.getPassword() == "") {
            return ResponseEntity.badRequest().headers(HeaderUtils.createFailureAlert("users", "password cannot be null or empty", "password cannot be null or empty")).body(null);
        }
        if (user.getUsername() == null || user.getUsername() == "") {
            return ResponseEntity.badRequest().headers(HeaderUtils.createFailureAlert("users", "Username cannot be null or empty", "Username cannot be null or empty")).body(null);
        }

        return ResponseEntity.ok("validation sucess");
    }

}
