package com.udea_ecomerce.backend.infraestructure.mapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.udea_ecomerce.backend.domain.model.OrderProduct;
import com.udea_ecomerce.backend.infraestructure.entity.OrderProductEntity;

@Mapper(componentModel = "spring")
public interface IOrderProductMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "product.id", target = "productId") // Mapeo desde el id de ProductEntity a productId
    
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    Iterable<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntities);

    @InheritInverseConfiguration
    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
     // Método para convertir Iterable<OrderProduct> a List<OrderProductEntity>
    default List<OrderProductEntity> toOrderProductEntityList(Iterable<OrderProduct> orderProducts) {
        return StreamSupport.stream(orderProducts.spliterator(), false)
                .map(this::toOrderProductEntity)
                .collect(Collectors.toList());
    }
}