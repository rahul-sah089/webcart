package com.hg.shoppingcart.webcart.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long basketNumber;
    private Long productCode;
    private Long quantity;
}
