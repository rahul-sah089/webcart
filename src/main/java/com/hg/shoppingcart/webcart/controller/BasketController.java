package com.hg.shoppingcart.webcart.controller;

import com.hg.shoppingcart.webcart.DTO.BasketDetailsDTO;
import com.hg.shoppingcart.webcart.DTO.CartDTO;
import com.hg.shoppingcart.webcart.model.Basket;
import com.hg.shoppingcart.webcart.model.BasketDetails;
import com.hg.shoppingcart.webcart.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BasketController {

    @Autowired
    BasketService basketItemService;

    @PostMapping(value = "/updateItems")
    public ResponseEntity<Basket> updateBasketItems(@RequestBody CartDTO cartDTO) {
        Basket basket = basketItemService.updateBasketItems(cartDTO);
        return ResponseEntity.ok(basket);
    }

    @GetMapping(value = "/fetchItems")
    public ResponseEntity<List<BasketDetailsDTO>> fetchItems(){
        List<BasketDetailsDTO> basketsDetails = basketItemService.getUserItems();
        System.out.println("**********baskt details**************");
        System.out.println(basketsDetails);
        System.out.println("**********baskt details**************");
        return ResponseEntity.ok(basketsDetails);
    }
}