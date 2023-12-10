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

    private UserRepository repository;
    private AccountService accountService;

    private UserService(UserRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public List<UserDto> getUsers(){
        List<User> users = repository.findAll();
        List<UserDto> usersDto = new ArrayList<UserDto>();
        for (User user: users) {
            if(!user.getDeleted()) {
                usersDto.add(UserMapper.userToDto(user));
            }
        }
        return usersDto;
    }

    public List<UserDto> getUsersDeleted(){
        List<User> users = repository.findAll();
        List<UserDto> usersDto = new ArrayList<UserDto>();
        for (User user: users) {
            if(user.getDeleted()) {
                usersDto.add(UserMapper.userToDto(user));
            }
        }
        return usersDto;
    }

    public UserDto getUserById(Long id){
        if(repository.existsById(id)) {
            User user = repository.findById(id).get();
            if(!user.getDeleted()) {
                user.setPassword("******");
                return UserMapper.userToDto(user);
            }
        }
        return null;
    }

    public UserDto createUser(UserDto dto){
        if(!repository.existsByEmail(dto.getEmail())) {
            dto.setCreated_at(LocalDateTime.now());
            dto.setUpdated_at(LocalDateTime.now());
            dto.setDeleted(false);
            User entitySaved = repository.save(UserMapper.dtoTouser(dto));
            dto = UserMapper.userToDto(entitySaved);
            dto.setPassword("******");
            repository.save(UserMapper.dtoTouser(dto));
            return dto;
        }
        return null;
    }

    //PUT
    public UserDto updateTotalUser(Long id, UserDto dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            if(!userToModify.getDeleted()) {
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
        }
        return null;
    }

    //PATCH
    public UserDto updateParcialUser(Long id, UserDto dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            if(!userToModify.getDeleted()) {
                if (dto.getUsername() != null) {
                    userToModify.setUsername(dto.getUsername());
                }
                if (dto.getEmail() != null && repository.findByEmail(dto.getEmail()) == null) {
                    userToModify.setEmail(dto.getEmail());
                }
                if (dto.getPassword() != null) {
                    userToModify.setPassword(dto.getPassword());
                }
                if (dto.getDni() != null) {
                    userToModify.setDni(dto.getDni());
                }
                if (dto.getAddress() != null) {
                    userToModify.setAddress(dto.getAddress());
                }
                if (dto.getBirthday_date() != null) {
                    userToModify.setBirthday_date(dto.getBirthday_date());
                }

                userToModify.setUpdated_at(LocalDateTime.now());
                User userModified = repository.save(userToModify);
                return UserMapper.userToDto(userModified);
            }
        }
        return null;
    }

    public String deleteUser(Long id){
        if (repository.existsById(id)){
            User userToDeleted = repository.findById(id).get();
            if(!userToDeleted.getDeleted()) {
                userToDeleted.setDeleted(true);
                repository.save(userToDeleted);
            }
            return "El usuario con id: " + id + " ha sido eliminado correctamente.";
        } else {
            return "El usuario con id: " + id + ", no existe.";
        }
    }

}