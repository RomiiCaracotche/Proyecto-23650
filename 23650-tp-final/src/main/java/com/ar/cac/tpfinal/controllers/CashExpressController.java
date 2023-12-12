package com.ar.cac.tpfinal.controllers;

import com.ar.cac.tpfinal.dtos.CashExpressDto;
import com.ar.cac.tpfinal.services.CashExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cash")
public class CashExpressController {
    @Autowired
    private CashExpressService service;

    @GetMapping
    public ResponseEntity<List<CashExpressDto>> getCashes() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCashes());
    }

    @GetMapping(value = "/{cbu}")
    public ResponseEntity<List<CashExpressDto>> getCashesByCbu(@PathVariable String cbu) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCashesByCbu(cbu));
    }

    @PostMapping
    public ResponseEntity<CashExpressDto> doCashExpress(@RequestBody CashExpressDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.doCashExpress(dto));
    }

}
