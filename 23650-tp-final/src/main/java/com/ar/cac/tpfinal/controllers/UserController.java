package com.ar.cac.tpfinal.controllers;

import com.ar.cac.tpfinal.dtos.UserDto;
import com.ar.cac.tpfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    //@Autowired
    private final UserService service;

    private UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    @GetMapping(value = "/deleted")
    public ResponseEntity<List<UserDto>> getUsersDeleted() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsersDeleted());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(dto));
    }

    // Modificacion total
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateTotalUser(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTotalUser(id, dto));
    }

    // Modificacion parcial

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateParcialUser(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateParcialUser(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));
    }
}

