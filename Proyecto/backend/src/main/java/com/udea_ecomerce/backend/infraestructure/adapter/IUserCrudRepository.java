package com.udea_ecomerce.backend.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.udea_ecomerce.backend.infraestructure.entity.UserEntity;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer>{
    
}
