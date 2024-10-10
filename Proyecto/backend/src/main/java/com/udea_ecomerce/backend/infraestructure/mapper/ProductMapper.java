package com.udea_ecomerce.backend.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.udea_ecomerce.backend.domain.model.Product;
import com.udea_ecomerce.backend.infraestructure.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source="description", target="description"),
            @Mapping(source="urlImage", target="urlImage"),
            @Mapping(source="price", target="price"),
            @Mapping(source="dateCreated", target="dateCreated"),
            @Mapping(source="dateUpdated", target="dateUpdated"),
            @Mapping(source="userEntity.id", target="userId"),
            @Mapping(source="categoryEntity.id", target="categoryId")

    })
    Product toProduct(ProductEntity productEntity);
    Iterable<Product> toProductList(Iterable<ProductEntity>productEntities);
   
    @InheritInverseConfiguration
    ProductEntity toProductEntity(Product product);
}

