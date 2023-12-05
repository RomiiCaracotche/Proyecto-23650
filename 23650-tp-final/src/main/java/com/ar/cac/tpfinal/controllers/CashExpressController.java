package com.ar.cac.tpfinal.controllers;

import com.ar.cac.tpfinal.dtos.CashExpressDto;
import com.ar.cac.tpfinal.dtos.UserDto;
import com.ar.cac.tpfinal.services.CashExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cash")
public class CashExpressController {
    @Autowired
    private CashExpressService service;

    @PostMapping(value = "/extract")
    public ResponseEntity<CashExpressDto> extractCashExpress(@RequestBody CashExpressDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.extractCashExpress(dto));
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<CashExpressDto> depositCashExpress(@RequestBody CashExpressDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.depositCashExpress(dto));
    }

}
