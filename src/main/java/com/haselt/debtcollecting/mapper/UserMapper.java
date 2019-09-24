package com.haselt.debtcollecting.mapper;

import com.haselt.debtcollecting.dto.UserDto;
import com.haselt.debtcollecting.entity.User;

public class UserMapper {

    public static User dtoToEntity(UserDto userDto){
        User user = new User()
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail());
        return user;
    }

    public static UserDto entityToDto(User user){
        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .id(user.getId())
                .build();
        return userDto;
    }
}
