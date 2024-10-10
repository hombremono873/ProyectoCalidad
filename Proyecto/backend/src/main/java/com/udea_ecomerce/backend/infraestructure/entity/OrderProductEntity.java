package com.udea_ecomerce.backend.infraestructure.entity;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="order_products")
@Data
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    //private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
  
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "orderentityid", nullable = true)
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable = true)
    private OrderEntity orderEntity;
}
