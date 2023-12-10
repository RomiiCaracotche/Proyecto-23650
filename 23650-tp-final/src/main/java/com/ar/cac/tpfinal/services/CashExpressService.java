package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.CashExpressDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.CashExpress;
import com.ar.cac.tpfinal.mappers.CashExpressMapper;
import com.ar.cac.tpfinal.repositories.AccountRepository;
import com.ar.cac.tpfinal.repositories.CashExpressRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CashExpressService {

    private CashExpressRepository repository;
    private AccountRepository accountRepository;

    private CashExpressService (CashExpressRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    public List<CashExpressDto> getCashesByCbu(String cbu){
        List<CashExpress> cashes = repository.findAll();
        List<CashExpressDto> cashesDto = new ArrayList<CashExpressDto>();
        for (CashExpress cash: cashes) {
            if(cash.getOrigin().equals(cbu)) {
                cashesDto.add(CashExpressMapper.cashToDto(cash));
            }
        }
        return cashesDto;
    }


    public CashExpressDto doCashExpress(CashExpressDto dto) {
        if(dto.getType().equals("extraer")) {
            CashExpressDto cashDto = extraer(dto);
            return cashDto;
        }
        if(dto.getType().equals("depositar")) {
            CashExpressDto cashDto = depositar(dto);
            return cashDto;
        }
        return null;
    }

    private CashExpressDto extraer(CashExpressDto dto){
        Account account;
        CashExpressDto cashDto = new CashExpressDto();

        if (accountRepository.existsByCbu(dto.getOrigin())) {
            account = accountRepository.findByCbu(dto.getOrigin());
            cashDto = doExtraer(account, dto);
        }
        if(accountRepository.existsByAlias(dto.getOrigin())) {
            account = accountRepository.findByAlias(dto.getOrigin());
            cashDto = doExtraer(account, dto);
        }
        return cashDto;
    }

    private CashExpressDto doExtraer(Account account, CashExpressDto dto) {
        if (account.getAmount().compareTo(dto.getAmount()) == 1 || account.getAmount().compareTo(dto.getAmount()) == 0) {
            BigDecimal montoRestante = account.getAmount().subtract(dto.getAmount());
            account.setAmount(montoRestante);
            accountRepository.save(account);
            dto.setDate(LocalDateTime.now());
            CashExpress cash = repository.save(CashExpressMapper.dtoToCash(dto));
            return CashExpressMapper.cashToDto(cash);
        }
        return null;
    }

    private CashExpressDto depositar(CashExpressDto dto){
        Account account;
        CashExpressDto cashDto = new CashExpressDto();

        if (accountRepository.existsByCbu(dto.getOrigin())) {
            account = accountRepository.findByCbu(dto.getOrigin());
            cashDto = doDepositar(account, dto);
        }
        if(accountRepository.existsByAlias(dto.getOrigin())) {
            account = accountRepository.findByAlias(dto.getOrigin());
            cashDto = doDepositar(account, dto);
        }
        return cashDto;
    }

    private CashExpressDto doDepositar(Account account, CashExpressDto dto) {
            account.setAmount(account.getAmount().add(dto.getAmount()));
            accountRepository.save(account);
            dto.setDate(LocalDateTime.now());
            CashExpress cash = repository.save(CashExpressMapper.dtoToCash(dto));
            return CashExpressMapper.cashToDto(cash);
    }
}
