package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.CashExpressDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.CashExpress;
import com.ar.cac.tpfinal.mappers.CashExpressMapper;
import com.ar.cac.tpfinal.mappers.AccountMapper;
import com.ar.cac.tpfinal.repositories.AccountRepository;
import com.ar.cac.tpfinal.repositories.CashExpressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashExpressService {

    @Autowired
    private CashExpressRepository repository;
    private AccountRepository accountRepository;

    public CashExpressDto extractCashExpress(CashExpressDto dto) {
        CashExpress cash = new CashExpress();
        //EXTRAER

        Account account = accountRepository.findByCbu(dto.getCbu());
        if (account != null) {
            if (account.getAmount().compareTo(dto.getMonto()) == 1 || account.getAmount().compareTo(dto.getMonto()) == 0) {
                BigDecimal montoExtraido = account.getAmount().subtract(dto.getMonto());
                account.setAmount(montoExtraido);
                accountRepository.save(account);
                cash.setFecha(LocalDateTime.now());
                cash.setNombre(dto.getNombre());
                cash.setDireccion(dto.getDireccion());
                cash.setCbu(dto.getCbu());
                cash.setMonto(montoExtraido);
                return CashExpressMapper.cashToDto(cash);
            }
        }
        return dto;
    }

    public CashExpressDto depositCashExpress(CashExpressDto dto) {
        CashExpress cash = new CashExpress();
        //DEPOSITAR

        Account account = accountRepository.findByCbu(dto.getCbu());
        if (account != null) {
            BigDecimal montoDepositado = account.getAmount().add(dto.getMonto());
            account.setAmount(montoDepositado);
            accountRepository.save(account);
            cash.setFecha(LocalDateTime.now());
            cash.setNombre(dto.getNombre());
            cash.setDireccion(dto.getDireccion());
            cash.setCbu(dto.getCbu());
            cash.setMonto(montoDepositado);
            return CashExpressMapper.cashToDto(cash);
        }
        return null;
    }

}
