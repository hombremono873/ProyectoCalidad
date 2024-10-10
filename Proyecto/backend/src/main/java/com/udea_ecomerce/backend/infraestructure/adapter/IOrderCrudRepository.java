package com.udea_ecomerce.backend.infraestructure.adapter;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udea_ecomerce.backend.domain.model.OrderState;
import com.udea_ecomerce.backend.infraestructure.entity.OrderEntity;

import jakarta.transaction.Transactional;


public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer>{
      @Transactional
      @Modifying
      @Query("UPDATE OrderEntity o SET o.orderState =: state WHERE o.id =: id")
      void upDateStateById(Integer id, OrderState state);
      Iterable<OrderEntity> findByUserId(Integer userId);;
}
