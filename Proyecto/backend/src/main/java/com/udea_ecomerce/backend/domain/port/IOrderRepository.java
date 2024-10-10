package com.udea_ecomerce.backend.domain.port;


import com.udea_ecomerce.backend.domain.model.Order;
//import com.udea_ecomerce.backend.infraestructure.entity.OrderEntity;
import com.udea_ecomerce.backend.infraestructure.entity.OrderEntity;

public interface IOrderRepository {
     Order save(Order order);
     Order findById(Integer id);
     Iterable<Order> findAll();
     void upDateStateById(Integer id, String state);
     Iterable<OrderEntity> findByUserId(Integer userId);
}
