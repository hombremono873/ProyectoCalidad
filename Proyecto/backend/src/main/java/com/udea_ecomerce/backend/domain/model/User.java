package com.udea_ecomerce.backend.domain.model;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/***
 * Coloca en auto getter/setter
 */
@AllArgsConstructor   //Agrega un constructor en automatico
@NoArgsConstructor    //Agrega constructor vacío
@Data
public class User {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String cellphone;
    private String password;
    private UserType userType;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

}
