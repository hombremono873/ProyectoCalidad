package com.udea_ecomerce.backend.infraestructure.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea_ecomerce.backend.applications.CategoryService;
import com.udea_ecomerce.backend.domain.model.Category;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
    * El uso deResponseEntity permite tener un metodo mas robusto
    * */
    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        log.info("Guardando categoría: {}", category);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ValorPersonalizado");
        return new ResponseEntity<>(categoryService.save(category), headers, HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public Iterable<Category> findAll() {
        log.info("Buscando todas las categorías desde el servicio");
        return categoryService.findAll();
    }
  
    @GetMapping("/findById/{id}")
    public Category findById(@PathVariable Integer id) {
        log.info("Buscando categoría por id: {}", id);
        return categoryService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Integer id) {
        log.info("Eliminando categoría con id: {}", id);
        this.categoryService.deleteById(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }
    
}
