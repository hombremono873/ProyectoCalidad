package com.udea_ecomerce.backend.infraestructure.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.udea_ecomerce.backend.domain.model.OrderState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

     @Column(name = "userentity_id")
     private Long userId; 

    @ManyToOne
    @JoinColumn(name = "userentity_id", insertable = false, updatable = false)
    private UserEntity userEntity;
    
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductEntity> orderProductentitys = new ArrayList<>();
    
   @PostPersist
    public void handlePostPersist() {
        // Puedes agregar lógica aquí que deseas ejecutar después de persistir la orden
        for (OrderProductEntity orderProductentity : orderProductentitys) {
            orderProductentity.setOrderEntity(this); // Asegura que cada producto tenga esta orden asociada
        }
    }
    
}