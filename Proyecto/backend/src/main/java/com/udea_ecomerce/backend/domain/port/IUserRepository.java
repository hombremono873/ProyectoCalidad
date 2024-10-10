package com.udea_ecomerce.backend.domain.port;

import com.udea_ecomerce.backend.domain.model.User;

public interface IUserRepository {
    User save(User user);
    User findByEmail(String email);
    User findById(Integer id);
    void deleteById(Integer id);
}
