package com.hg.shoppingcart.webcart.repositories;

import com.hg.shoppingcart.webcart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
