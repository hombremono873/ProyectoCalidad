package com.udea_ecomerce.backend.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProduct {
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer productId;
    public BigDecimal getTotalProduct(){
        return this.price.multiply(this.quantity);
    }
}
