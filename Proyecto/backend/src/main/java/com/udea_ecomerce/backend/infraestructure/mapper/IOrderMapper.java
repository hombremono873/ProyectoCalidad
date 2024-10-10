package com.udea_ecomerce.backend.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.udea_ecomerce.backend.domain.model.Order;
import com.udea_ecomerce.backend.infraestructure.entity.OrderEntity;

@Mapper(componentModel = "spring",uses={IOrderProductMapper.class})
public interface IOrderMapper {
   @Mappings(
        {
             @Mapping(source = "id", target = "id"),
             @Mapping(source = "dateCreated", target="dateCreated"),
             @Mapping(source = "orderProductentitys", target="orderProducts"),
             @Mapping(source = "orderState", target="orderState" ),
             @Mapping(source = "userId", target="userId" )
        }
    )
    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
     OrderEntity toOrderEntity(Order order);
}
