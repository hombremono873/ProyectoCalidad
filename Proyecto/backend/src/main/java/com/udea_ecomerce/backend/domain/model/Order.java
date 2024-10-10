package com.udea_ecomerce.backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.udea_ecomerce.backend.infraestructure.entity.UserEntity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private LocalDateTime dateCreated;
    private List<OrderProduct> orderProducts;
    private OrderState orderState;
    private Integer userId;
    private UserEntity userEntity;
    public Order() {
        this.orderProducts = new ArrayList<>();
    }

    public BigDecimal getTotalPrice() {
        //BigDecimal totalPrice = BigDecimal.ZERO;
        return this.orderProducts.stream().map( ordenProduct -> ordenProduct.getTotalProduct() ).reduce(BigDecimal.ZERO,BigDecimal::add);
        /*for (OrderProduct product : orderProducts) {
            totalPrice = totalPrice.add(product.getTotalProduct());
        }
        return totalPrice;*/
    }

}