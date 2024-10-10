package com.udea_ecomerce.backend.applications;

//import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.udea_ecomerce.backend.domain.model.Category;
import com.udea_ecomerce.backend.domain.port.ICategoryRepository;
@Service
public class CategoryService {
    private final ICategoryRepository icategory;

    public CategoryService(ICategoryRepository icategory){
        this.icategory = icategory;
    }
    public Category save(Category category){
        return this.icategory.save(category);
    }

    public Iterable<Category> findAll(){
        return this.icategory.findAll();
    }
    public Category findById(Integer id){
        return this.icategory.findById(id);
    }
    
    public void deleteById(Integer id){
        this.icategory.deleteById(id);
    }
    public Category updateCategory(Integer id, Category category) {
        // Verificar si la categoría existe
        Category existingCategory = this.icategory.findById(id);

        if (existingCategory != null) {
            // Actualizar los campos necesarios
            existingCategory.setName(category.getName());
            // Actualiza otros campos si es necesario

            // Guardar la categoría actualizada
            return this.icategory.save(existingCategory);
        } else {
            // La categoría no existe
            return null; // Puedes manejar el caso de no existencia de otra manera si es necesario
        }
    }

}
