package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.entities.User;
import com.ar.cac.tpfinal.dtos.UserDto;
import com.ar.cac.tpfinal.mappers.UserMapper;
import com.ar.cac.tpfinal.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<UserDto> getUsers(){
        List<User> users = repository.findAll();
        List<UserDto> usersDto = new ArrayList<UserDto>();
        for (User user: users) {
            usersDto.add(UserMapper.userToDto(user));
        }
        return usersDto;
    }

    public UserDto getUserById(Long id){
        User user = repository.findById(id).get();
        user.setPassword("******");
        return UserMapper.userToDto(user);
    }

    public UserDto createUser(UserDto user){
        // TODO: validacion de email existente

        User entitySaved = repository.save(UserMapper.dtoTouser(user));
        user = UserMapper.userToDto(entitySaved);
        user.setPassword("******");
        return user;
    }

    //Put
    public UserDto updateTotalUser(Long id, UserDto dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            userToModify.setUsername(dto.getUsername());
            userToModify.setPassword(dto.getPassword());
            userToModify.setEmail(dto.getEmail());
            userToModify.setDni(dto.getDni());
            userToModify.setAddress(dto.getAddress());
            userToModify.setBirthday_date(dto.getBirthday_date());
            userToModify.setUpdated_at(LocalDateTime.now());
            User userModified = repository.save(userToModify);
            return UserMapper.userToDto(userModified);
        }
        return null;
    }

    //Patch
    public UserDto updateParcialUser(Long id, UserDto dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();

            if (dto.getUsername() != null){
                userToModify.setUsername(dto.getUsername());
            }
            if (dto.getEmail() != null && validateUserByEmail(dto) == null){
                userToModify.setEmail(dto.getEmail());
            }
            if (dto.getPassword() != null){
                userToModify.setPassword(dto.getPassword());
            }
            if (dto.getDni() != null){
                userToModify.setDni(dto.getDni());
            }
            if (dto.getAddress() != null){
                userToModify.setAddress(dto.getAddress());
            }
            if (dto.getBirthday_date() != null){
                userToModify.setBirthday_date(dto.getBirthday_date());
            }

            userToModify.setUpdated_at(LocalDateTime.now());
            User userModified = repository.save(userToModify);
            return UserMapper.userToDto(userModified);
        }
        return null;
    }

    // TODO: hacer aca baja logica
    public String deleteUser(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado correctamente.";
        } else {
            return "El usuario con id: " + id + ", no existe.";
        }
    }

    public User validateUserByEmail(UserDto dto){
        return repository.findByEmail(dto.getEmail());
    }

}