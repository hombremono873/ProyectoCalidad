package com.udea_ecomerce.backend.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.udea_ecomerce.backend.domain.model.User;
import com.udea_ecomerce.backend.infraestructure.entity.UserEntity;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Mapea un objeto User a UserEntity
     * @param user El usuario a mapear
     * @return UserEntity mapeado
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "cellphone", target = "cellphone"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "userType", target = "userType"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated")
    })
    User toUser(UserEntity userEntity);
    Iterable<User> toUsers( Iterable<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);
}