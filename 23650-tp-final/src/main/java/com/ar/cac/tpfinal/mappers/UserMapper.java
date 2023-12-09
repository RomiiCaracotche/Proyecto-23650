package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.entities.User;
import com.ar.cac.tpfinal.dtos.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User dtoTouser(UserDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setDni(dto.getDni());
        user.setAddress(dto.getAddress());
        user.setBirthday_date(dto.getBirthday_date());
        user.setAccounts(dto.getAccounts());
        user.setDeleted(dto.getDeleted());
        user.setCreated_at(dto.getCreated_at());
        user.setUpdated_at(dto.getUpdated_at());

        return user;
    }

    public static UserDto userToDto(User user){
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setDni(user.getDni());
        dto.setAddress(user.getAddress());
        dto.setBirthday_date(user.getBirthday_date());
        dto.setAccounts(user.getAccounts());
        dto.setDeleted(user.getDeleted());
        dto.setCreated_at(user.getCreated_at());
        dto.setUpdated_at(user.getUpdated_at());

        return dto;
    }
}
