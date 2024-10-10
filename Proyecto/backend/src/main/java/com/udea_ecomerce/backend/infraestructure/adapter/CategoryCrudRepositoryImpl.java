package com.udea_ecomerce.backend.infraestructure.adapter;



import org.springframework.stereotype.Repository;

import com.udea_ecomerce.backend.domain.model.Category;
import com.udea_ecomerce.backend.domain.port.ICategoryRepository;
import com.udea_ecomerce.backend.infraestructure.mapper.CategoryMapper;

/***
 * Debe estar anotada como repository para que spring la detecte como un bean de repositorio esto
 * Esto le permite a spring gestionarla e inyectarla donde sea necesario
 */
@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository{
     
    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper){
           this.categoryMapper = categoryMapper;
           this.iCategoryCrudRepository = iCategoryCrudRepository; 
    }

    @Override
    public Category save(Category category) {
       return this.categoryMapper.toCategory(this.iCategoryCrudRepository.save(this.categoryMapper.toCategoryEntity(category)));

    }

    @Override
    public Iterable<Category> findAll() {
       return this.categoryMapper.toCategoryList(this.iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        //return  categoryMapper.toCategory(this.iCategoryCrudRepository.findById(id).get());
        return this.categoryMapper.toCategory(this.iCategoryCrudRepository.findById(id).orElse(null)); //Optimo
    }

    @Override
    public void deleteById(Integer id) {
         this.iCategoryCrudRepository.deleteById(id);
    }



}
