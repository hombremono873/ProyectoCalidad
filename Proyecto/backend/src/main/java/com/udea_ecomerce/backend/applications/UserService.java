package com.udea_ecomerce.backend.applications;

import org.springframework.stereotype.Service;

import com.udea_ecomerce.backend.domain.model.User;
import com.udea_ecomerce.backend.domain.port.IUserRepository;
@Service
public class UserService {
    private final IUserRepository iuserrep;
    
    public UserService(IUserRepository userRep){
        this.iuserrep = userRep;
    }
    
    public User save(User user){
       return  this.iuserrep.save(user);
    }
    public User findById(Integer id){
        return this.iuserrep.findById(id);
    }

    public void deleteById(Integer id){
        this.iuserrep.deleteById(id);
    }
}
