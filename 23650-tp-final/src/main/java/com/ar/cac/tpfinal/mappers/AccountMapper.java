package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.Enums.AccountType;
import com.ar.cac.tpfinal.entities.User;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class AccountMapper {

    public static AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();

        dto.setId(account.getId());
        dto.setType(account.getType());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        dto.setReferencia(account.getReferencia());

        dto.setOwner(account.getOwner());

        return dto;
    }

    public static Account dtoToAccount(AccountDto dto){
        Account account = new Account();

        account.setAlias(dto.getAlias());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu());
        account.setAmount(dto.getAmount());
        account.setReferencia(dto.getReferencia());

        account.setOwner(dto.getOwner());
        return account;
    }
}
