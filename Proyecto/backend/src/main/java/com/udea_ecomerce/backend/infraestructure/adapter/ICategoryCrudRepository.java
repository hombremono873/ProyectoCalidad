package com.udea_ecomerce.backend.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;
import com.udea_ecomerce.backend.infraestructure.entity.CategoryEntity;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer>{

}
