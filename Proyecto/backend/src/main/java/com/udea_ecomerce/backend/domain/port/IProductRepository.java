package com.udea_ecomerce.backend.domain.port;

import com.udea_ecomerce.backend.domain.model.Product;

public interface IProductRepository {
    Product save(Product product);
    Iterable<Product> findAll();
    Product findById(Integer id);
    void deleteById(Integer id);
}
