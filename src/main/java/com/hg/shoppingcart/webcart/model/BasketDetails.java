package com.hg.shoppingcart.webcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "basket_details")
public class BasketDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BASKET_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "BASKET_DETAIL_BASKET_FK"))
    private Basket basket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "BASKET_DETAIL_PROD_FK"))
    private Products product;

    @Column(name = "Quanity", nullable = false)
    private Long quanity;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Amount", nullable = false)
    private double amount;

}
