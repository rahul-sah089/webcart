package com.hg.shoppingcart.webcart.repositories;

import com.hg.shoppingcart.webcart.model.BasketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketDetailsRepository extends JpaRepository<BasketDetails, Long> {
    @Query("FROM BasketDetails bd where bd.basket.id = :basketId")
    List<BasketDetails> findAllByBasketId(@Param("basketId") Long basketId);

}
