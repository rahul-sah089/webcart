package com.hg.shoppingcart.webcart.controller;

import com.hg.shoppingcart.webcart.DTO.CartDTO;
import com.hg.shoppingcart.webcart.model.Basket;
import com.hg.shoppingcart.webcart.model.BasketDetails;
import com.hg.shoppingcart.webcart.model.Products;
import com.hg.shoppingcart.webcart.services.BasketService;
import com.hg.shoppingcart.webcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/getProducts")
    public ResponseEntity<List<Products>> getProducts() {
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

}
