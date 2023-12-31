package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.entities.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public static AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();

        dto.setId(account.getId());
        dto.setType(account.getType());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        dto.setDeleted(account.getDeleted());
        dto.setOwner(account.getOwner());
        dto.setCreated_at(account.getCreated_at());
        dto.setUpdated_at(account.getCreated_at());
        dto.setTransfers(account.getTransfers());

        return dto;
    }

    public static Account dtoToAccount(AccountDto dto){
        Account account = new Account();

        account.setId(dto.getId());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setAmount(dto.getAmount());
        account.setDeleted(dto.getDeleted());
        account.setOwner(dto.getOwner());
        account.setCreated_at(dto.getCreated_at());
        account.setUpdated_at(dto.getCreated_at());
        account.setTransfers(dto.getTransfers());

        return account;
    }
}
