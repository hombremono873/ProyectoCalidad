package com.udea_ecomerce.backend.infraestructure.adapter;

import org.springframework.stereotype.Repository;

import com.udea_ecomerce.backend.domain.model.Product;
import com.udea_ecomerce.backend.domain.port.IProductRepository;
import com.udea_ecomerce.backend.infraestructure.mapper.ProductMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor //La inyeccion de variables es en auto
public class ProductCrudRepositoryImpl implements IProductRepository{
    private final IProductCrudRepository iProductCrudRepository;
    private final ProductMapper productMapper;
    @Override
    public Product save(Product product) {
        this.iProductCrudRepository.save(productMapper.toProductEntity(product));
        return product;
    }

    @Override
    public Iterable<Product> findAll() {
        return  this.productMapper.toProductList(this.iProductCrudRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return this.productMapper.toProduct(this.iProductCrudRepository.findById(id).orElse(null));//optimizado
    }

    @Override
    public void deleteById(Integer id) {
        this.iProductCrudRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto con id: " + id + " no existe"));
        this.iProductCrudRepository.deleteById(id);
    }

}
