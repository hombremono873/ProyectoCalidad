package com.udea_ecomerce.backend.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udea_ecomerce.backend.applications.CategoryService;
import com.udea_ecomerce.backend.applications.OrderService;
import com.udea_ecomerce.backend.applications.ProductService;
import com.udea_ecomerce.backend.applications.UploadFile;
import com.udea_ecomerce.backend.applications.UserService;
import com.udea_ecomerce.backend.domain.port.ICategoryRepository;
import com.udea_ecomerce.backend.domain.port.IOrderRepository;
import com.udea_ecomerce.backend.domain.port.IProductRepository;
import com.udea_ecomerce.backend.domain.port.IUserRepository;

/*
 *   En nuestra clase Beanconfiguration estamos creando un bean del tipo UserService
     *   para que la gstione el contenedor de SpringBoot y la inyecte donde sea necesario
     *   La creacion del bean conciste en crear un metodo en la clase de configuracion que retorne
     *   el objeto bean UserService. Recordemos que la clase UserService implementa los metodos para loe endpoint
     *   Que seran  usados en el controlador, es decir los metodos de la clase del servicio no se usan directamente
     *   de la clase UserService
 */
@Configuration
public class BeanConfiguration {
   @Bean
   public UserService userServicio(IUserRepository iUserRepository){
      return new UserService(iUserRepository);
   }
   @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository) {
        return new CategoryService(iCategoryRepository);
    }
     @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFile uploadFile){
        return  new ProductService(iProductRepository, uploadFile);
    }
    /***
     * 
     * @param iOrderRepository
     * @return
     * Cuando se crea el bean Es importante colocar como parametro los mismos que estan en
     * el constructor como en este caso en OrderSrvice que espera un parametro de tipo
     * IorderRepository
     */
    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository ){
        return new OrderService(iOrderRepository);
    }
    @Bean
    public UploadFile uploadFile(){
        return new UploadFile();
    }
}

