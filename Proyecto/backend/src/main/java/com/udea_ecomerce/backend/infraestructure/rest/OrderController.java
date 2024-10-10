package com.udea_ecomerce.backend.infraestructure.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udea_ecomerce.backend.applications.OrderService;
import com.udea_ecomerce.backend.domain.model.Order;
import com.udea_ecomerce.backend.domain.model.OrderState;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/orders/")
@Slf4j


public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
         this.orderService = orderService;
    }
    
   
    @Transactional
    @PostMapping("save")
    public ResponseEntity<Order> save(@RequestBody Order order) {
        try {
            // Aquí podrías realizar validaciones adicionales si es necesario
            if (order.getOrderState() == OrderState.CANCELLED) {
                order.setOrderState(OrderState.CANCELLED);
            } else {
                order.setOrderState(OrderState.ACTIVE); // Establece el estado a ACTIVE si no está cancelado
            }

            Order savedOrder = orderService.save(order);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            log.error("Error al guardar la orden: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("updateOrder")
    public void updateStateById(@RequestParam Integer id, @RequestParam String state){
         this.orderService.updateStateById(id, state);; 
    }
    @GetMapping("getAll")
    public List<Order> findAll() {
        Iterable<Order> ordersIterable = this.orderService.findAll();
        List<Order> ordersList = new ArrayList<>();
        ordersIterable.forEach(ordersList::add);
        return ordersList;      
    }
    @GetMapping("findById/{id}")
    public Order findById(@PathVariable Integer id) {
        return this.orderService.findById(id);
    }

}