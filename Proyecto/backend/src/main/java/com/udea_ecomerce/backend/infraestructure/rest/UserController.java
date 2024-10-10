package com.udea_ecomerce.backend.infraestructure.rest;

import org.springframework.web.bind.annotation.*;

import com.udea_ecomerce.backend.applications.UserService;
import com.udea_ecomerce.backend.domain.model.User;

@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("save")
    public User save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping("findById/{id}")
    public User findById(@PathVariable Integer id) {
        return this.userService.findById(id);
    }
    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable Integer id){
       this.userService.deleteById(id);
    }
}