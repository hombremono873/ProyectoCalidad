package com.udea_ecomerce.backend.infraestructure.rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import com.udea_ecomerce.backend.applications.ProductService;
import com.udea_ecomerce.backend.domain.model.Product;
import java.io.IOException;
import java.util.List; // Importa la clase List
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product") // Eliminada la barra final
@Slf4j
//@CrossOrigin("http://localhost:4200") // Esto funciona tambien pero es menos general desde el main el Cross es global
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    /***
     * 
     * @param product
     * @return
     * Se produce error por que desde Angular se envía un formData y el metodo espera un 
     * parametro tipo formData, Es de observar que desde la frontend se envía un tipo de datos String
     * Por lo que los campos que son numericos se transforman a el tipo correspondiente
     */

    /*@PostMapping("/save")
    public Product save(@RequestBody Product product) {
        log.info("Se guarda el producto {}", product.getName());
        return this.productService.save(product);
    }*/
    @PostMapping("/save")
    public ResponseEntity<Product> save(
        @RequestParam(required = false) Integer id,
        @RequestParam String name,
        @RequestParam String code,
        @RequestParam String description,
        @RequestParam BigDecimal price,
        @RequestParam(value = "image",required = false) MultipartFile multipartFile,
        @RequestParam Integer userId,
        @RequestParam Integer categoryId
      ) {
        Product product = new Product();
        if (id != null) {
            product.setId(id);
        }
        product.setName(name);
        product.setCode(code);
        
        product.setDescription(description);
        product.setPrice(price);
        product.setUserId(userId);
        product.setCategoryId(categoryId);

        try {
            Product savedProduct = productService.save(product, multipartFile);
            log.info("Producto guardado: {}", savedProduct.getName());
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Error al guardar el producto: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteByID/{id}")
    public void deleteById(@PathVariable Integer id) {
        this.productService.deleteById(id);
    }
    @GetMapping("/findAll")
    public List<Product> findAll() {
        log.info("Fetching all products");
        return (List<Product>) productService.getProducts();
    } 
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
       log.info("Fetching product by ID: {}", id);
       return this.productService.findById(id);
    }

}