package com.ar.cac.tpfinal.controllers;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccounts());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    }

    @GetMapping(value = "/cbu/{cbu}")
    public ResponseEntity<AccountDto> getAccountByCbu(@PathVariable String cbu){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountByCbu(cbu));
    }

    @GetMapping(value = "/deleted")
    public ResponseEntity<List<AccountDto>> getAccountsDeleted() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountsDeleted());
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.createAccount(dto));
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }

}
