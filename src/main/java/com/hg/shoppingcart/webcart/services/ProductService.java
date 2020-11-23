package com.hg.shoppingcart.webcart.services;

import com.hg.shoppingcart.webcart.model.Products;
import com.hg.shoppingcart.webcart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductService")
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Products> getAllProducts() {
        List<Products> products = productRepository.findAll();
        return products;
    }
}
