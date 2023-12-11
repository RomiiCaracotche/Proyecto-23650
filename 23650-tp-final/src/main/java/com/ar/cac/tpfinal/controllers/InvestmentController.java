package com.ar.cac.tpfinal.controllers;

import com.ar.cac.tpfinal.dtos.InvestmentDto;
import com.ar.cac.tpfinal.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService service;


    @GetMapping
    public ResponseEntity<List<InvestmentDto>> getInvestments() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getInvestments());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InvestmentDto> getInvestmentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getInvestmentById(id));
    }

    @GetMapping(value = "/cbu/{cbu}")
    public ResponseEntity<List<InvestmentDto>> getInvestmentByCbu(@PathVariable String cbu) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getInvestmentByCbu(cbu));
    }

    @PostMapping
    public ResponseEntity<InvestmentDto> createInvestment(@RequestBody InvestmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createInvestment(dto));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteInvestment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteInvestment(id));
    }
}
