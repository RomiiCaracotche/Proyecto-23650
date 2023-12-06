package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.entities.User;
import com.ar.cac.tpfinal.dtos.UserDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserMapper {

    public static User dtoTouser(UserDto dto){
        User user = new User(null, dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getDni(), dto.getAddress(), dto.getBirthday_date(), LocalDateTime.now(), LocalDateTime.now(), dto.getAccounts(), false);
        return user;
    }

    public static UserDto userToDto(User user){
        UserDto dto = new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getDni(), user.getAddress(), user.getBirthday_date(), user.getCreated_at(), user.getUpdated_at(), user.getAccounts(), user.getDeleted());
        return dto;
    }
}
