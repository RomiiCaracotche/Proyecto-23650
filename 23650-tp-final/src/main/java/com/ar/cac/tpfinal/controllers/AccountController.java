package com.ar.cac.tpfinal.controllers;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.services.AccountService;
import com.ar.cac.tpfinal.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;
    private final UserService userService;

    private AccountController(AccountService service, UserService userService){
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccounts());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account){

        account.setOwner(userService.userToAssign(account.getReferencia()));
        ResponseEntity<AccountDto> accountCreada = ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));

        return accountCreada;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }

    //Hacer la baja logica
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }

}
