package com.ar.cac.tpfinal.controllers;

import com.ar.cac.tpfinal.dtos.TransferDto;
import com.ar.cac.tpfinal.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    private final TransferService service;

    private TransferController(TransferService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TransferDto>> getTransfers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDto> createTransfer(@RequestBody TransferDto transfer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }

}
