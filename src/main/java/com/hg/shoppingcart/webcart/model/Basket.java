package com.hg.shoppingcart.webcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "order_Date", nullable = false)
    private Date orderDate;

    @Column(name = "basket_Num", nullable = false)
    private String basketNumber;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "customer_username", length = 255, nullable = false)
    private String customerUserName;

    @Column(name="customer_email",length = 128,nullable = false)
    private String customerEmail;

}
