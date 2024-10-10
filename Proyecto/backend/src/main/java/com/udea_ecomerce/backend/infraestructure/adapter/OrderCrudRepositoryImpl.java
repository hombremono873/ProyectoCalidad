package com.udea_ecomerce.backend.infraestructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.udea_ecomerce.backend.domain.model.Order;
import com.udea_ecomerce.backend.domain.model.OrderState;
import com.udea_ecomerce.backend.domain.port.IOrderRepository;
import com.udea_ecomerce.backend.infraestructure.entity.OrderEntity;
import com.udea_ecomerce.backend.infraestructure.mapper.IOrderMapper;

import jakarta.transaction.Transactional;
@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository{
    private final IOrderCrudRepository iOrderCrudRepository;
    private final IOrderMapper iOrderMapper;
    public OrderCrudRepositoryImpl(IOrderCrudRepository iOrderCrudRepository, IOrderMapper iOrderMapper){
            this.iOrderCrudRepository = iOrderCrudRepository;
            this.iOrderMapper = iOrderMapper;
    }

    @Override
    public Order save(Order order) {
        return this.iOrderMapper.toOrder(this.iOrderCrudRepository.save(this.iOrderMapper.toOrderEntity(order)));
    }

    @Override
    public Order findById(Integer id) {
       return this.iOrderMapper.toOrder( this.iOrderCrudRepository.findById(id).orElse(null));
    }

    @Override
    public Iterable<Order> findAll() {
         return this.iOrderMapper.toOrderList(this.iOrderCrudRepository.findAll());
    }

   
    @Override
    public void upDateStateById(Integer id, String state) {
        if(state.equals(OrderState.CANCELLED)){
             this.iOrderCrudRepository.upDateStateById(id,OrderState.CANCELLED);     
        }
        else{
            this.iOrderCrudRepository.upDateStateById(id,OrderState.COMFIRMED); 
        }
    }

    @Override
    @Transactional
    public List<OrderEntity> findByUserId(Integer userId) {
        Iterable<OrderEntity> orderEntities = iOrderCrudRepository.findByUserId(userId);
        List<OrderEntity> orders = new ArrayList<>();
        
        for (OrderEntity orderEntity : orderEntities) {
            orders.add(orderEntity); // No se necesita mapeo, ya que orderEntity es del tipo correcto
        }
        
        return orders;
    }

   
   
}       



