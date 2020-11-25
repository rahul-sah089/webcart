package com.hg.shoppingcart.webcart.DTO;

import java.util.Date;

public class BasketDetailsDTO {
    private String productName;
    private Double productPrice;
    private Long productId;
    private Date orderDate;
    private Long quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BasketDetailsDTO(String productName, Double productPrice, Long productId, Date orderDate, Long quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productId = productId;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

}
