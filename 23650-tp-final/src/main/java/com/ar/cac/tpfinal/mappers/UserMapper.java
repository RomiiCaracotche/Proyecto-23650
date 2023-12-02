package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.entities.User;
import com.ar.cac.tpfinal.dtos.UserDto;
import java.time.LocalDateTime;

public class UserMapper {

    public static User dtoTouser(UserDto dto){
        User user = new User(null, dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getDni(), dto.getAddress(), dto.getBirthday_date(), LocalDateTime.now(), LocalDateTime.now(), dto.getAccounts());

        //User user = new User();
        //user.setUsername(dto.getUsername());
        //user.setPassword(dto.getPassword());
        //user.setAddress(dto.getAddress());
        //user.setDni(dto.getDni());
        //user.setBirthday_date(dto.getBirthday_date());
        //user.setEmail(dto.getEmail());
        //user.setCreated_at(LocalDateTime.now());
        //user.setUpdated_at(LocalDateTime.now());
        return user;
    }

    public static UserDto userToDto(User user){
        UserDto dto = new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getDni(), user.getAddress(), user.getBirthday_date(), user.getCreated_at(), user.getUpdated_at(), user.getAccounts());
        //UserDto dto = new UserDto();
        //dto.setUsername(user.getUsername());
        //dto.setPassword(user.getPassword());
        //dto.setId(user.getId());
        //dto.setAddress(user.getAddress());
        //dto.setEmail(user.getEmail());
        //dto.setBirthday_date(user.getBirthday_date());
        //dto.setDni(user.getDni());
        //dto.setCreated_at(user.getCreated_at());
        //dto.setUpdated_at(user.getUpdated_at());
        return dto;
    }
}
