package com.hg.shoppingcart.webcart.utils;

import com.hg.shoppingcart.webcart.DTO.BasketDetailsDTO;
import com.hg.shoppingcart.webcart.model.BasketDetails;

public class BasketDetailsToDTO {
    public static BasketDetailsDTO convertToDTO(BasketDetails basketDetails){
        BasketDetailsDTO basketDetailsDTO =  new BasketDetailsDTO(basketDetails.getProduct().getName(),
                basketDetails.getProduct().getPrice(),
                basketDetails.getProduct().getId(),
                basketDetails.getBasket().getOrderDate(),
                basketDetails.getQuanity());
        return basketDetailsDTO;
    }
}
