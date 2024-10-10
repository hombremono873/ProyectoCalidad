package com.udea_ecomerce.backend.infraestructure.adapter;


import org.springframework.stereotype.Repository;

import com.udea_ecomerce.backend.domain.model.User;

import com.udea_ecomerce.backend.domain.port.IUserRepository;
import com.udea_ecomerce.backend.infraestructure.mapper.UserMapper;


/***
 * Debe estar anotada como repository para que spring la detecte como un bean de repositorio esto
 * Esto le permite a spring gestionarla e inyectarla donde sea necesario
 */
@Repository
public class UserCrudRepositoryImpl implements IUserRepository{

    private final IUserCrudRepository iUserCrudRepository;
    private final UserMapper userMapper;
    
    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, UserMapper userMapper){
        this.userMapper = userMapper;
        this.iUserCrudRepository = iUserCrudRepository;
    }
    
    @Override
    public User save(User user) {
        iUserCrudRepository.save(userMapper.toUserEntity(user));
        //return  userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
        return user;
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public User findById(Integer id) {
        return userMapper.toUser(iUserCrudRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
       
    }

    
     

}
