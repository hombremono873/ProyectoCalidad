package com.udea_ecomerce.backend.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udea_ecomerce.backend.infraestructure.entity.ProductEntity;

@Repository
public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer>{

}
