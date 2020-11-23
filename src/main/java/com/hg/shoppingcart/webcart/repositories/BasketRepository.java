package com.hg.shoppingcart.webcart.repositories;

import com.hg.shoppingcart.webcart.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByCustomerUserName(String username);
}
