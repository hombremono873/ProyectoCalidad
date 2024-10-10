package com.udea_ecomerce.backend.applications;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udea_ecomerce.backend.domain.model.Product;
import com.udea_ecomerce.backend.domain.port.IProductRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j          //Implementa los log()
public class ProductService {
    private final UploadFile uploadFile;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final IProductRepository iproductRepository;

    public ProductService(IProductRepository iproductRepository, UploadFile uploadFile) {
        this.iproductRepository = iproductRepository;
        this.uploadFile = uploadFile;
    }
    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if (product.getId() != 0) {  // El producto no es nuevo, se modifica
            if (multipartFile == null) {
                product.setUrlImage(product.getUrlImage()); // Se setea la imagen existente
            } else {
                String nameFile = product.toString().substring(29);
                product.setUrlImage(uploadFile.upload(multipartFile));
                log.info("Eta es la imagen eliminada {}", nameFile);
                if(!nameFile.equals("default.jpg")){
                    uploadFile.delete(nameFile);
                }
            }
        } else {
            product.setUrlImage(uploadFile.upload(multipartFile));
        }
        
        logger.info("Saving product: {}", product);
        Product savedProduct = this.iproductRepository.save(product);
        return savedProduct;
    }
    

    public Iterable<Product> getProducts() {
        logger.info("Fetching all products");
        return this.iproductRepository.findAll();
    }

    public Product findById(Integer id) {
        logger.info("Fetching product by ID: {}", id);
        return this.iproductRepository.findById(id);
    }
    public void deleteById(Integer id) {
        Product product = findById(id);
        String nameFile = product.toString().substring(29);
        if(!nameFile.equals("default.jpg")){
            uploadFile.delete(nameFile);
        }
        log.info("Esta es la imagen eliminada {}", nameFile); 
        logger.info("Deleting product with ID: {}", id);
        this.iproductRepository.deleteById(id);
    }
}