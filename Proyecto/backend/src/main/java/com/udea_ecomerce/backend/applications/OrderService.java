package com.udea_ecomerce.backend.applications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.udea_ecomerce.backend.domain.model.Order;
import com.udea_ecomerce.backend.domain.port.IOrderRepository;
import com.udea_ecomerce.backend.infraestructure.entity.OrderEntity;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final IOrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;  
    }   
    @Transactional 
    public Order save(Order order) {
        logger.info("Saving order: {}", order);
        Order savedOrder = orderRepository.save(order);
        //this.orderProductEntity.setId(savedOrder.getId());
        logger.info("Omar es el detalle: {}", savedOrder.getOrderProducts());
        logger.info("Omar esto es  with ID: {}", savedOrder.getId());
        return savedOrder;
    }
    
   
    public void updateStateById(Integer id, String state) {
        orderRepository.upDateStateById(id, state);
    }
    public Iterable<Order> findAll(){
        return this.orderRepository.findAll();
    }

    public Iterable<OrderEntity> findUserOrdersById(Integer userId) {
        return orderRepository.findByUserId(userId);
    }
    public Order findById(Integer id){
       return this.orderRepository.findById(id);
    }
   }