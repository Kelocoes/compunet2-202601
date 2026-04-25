package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.example.demo.model.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Named("userToId")
    default Long userToId(User user) {
        return user != null ? user.getId() : null;
    }

    @Named("userToUsername")
    default String userToUsername(User user) {
        return user != null ? user.getUsername() : null;
    }
}