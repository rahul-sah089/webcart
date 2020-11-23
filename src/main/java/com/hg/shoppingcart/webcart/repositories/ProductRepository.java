package com.hg.shoppingcart.webcart.repositories;

import com.hg.shoppingcart.webcart.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {

}
