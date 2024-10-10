package com.udea_ecomerce.backend.domain.port;

import com.udea_ecomerce.backend.domain.model.Category;

public interface ICategoryRepository {
   Category save(Category category);
   Iterable<Category> findAll();
   Category findById(Integer id);
    
   void deleteById(Integer id);
}
