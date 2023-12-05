package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.dtos.UserDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.Transfer;
import com.ar.cac.tpfinal.entities.User;
import com.ar.cac.tpfinal.mappers.AccountMapper;
import com.ar.cac.tpfinal.mappers.UserMapper;
import com.ar.cac.tpfinal.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public List<AccountDto> getAccounts() {
        List<Account> accounts = repository.findAll();
        List<AccountDto> accountDto = new ArrayList<AccountDto>();
        for (Account account: accounts) {
            if(account.getDeleted() == false) {
                accountDto.add(AccountMapper.accountToDto(account));
            }
        }
        return accountDto;
    }

    public AccountDto getAccountById(Long id) {
        Account account = repository.findById(id).get();
        return AccountMapper.accountToDto(account);
    }

    public AccountDto getAccountByCbu(String cbu) {
        Account account = repository.findByCbu(cbu);
        return AccountMapper.accountToDto(account);
    }

    public List<AccountDto> getAccountsDeleted(){
        List<Account> accounts = repository.findAll();
        List<AccountDto> accountsDto = new ArrayList<AccountDto>();
        for (Account account: accounts) {
            if(account.getDeleted()) {
                accountsDto.add(AccountMapper.accountToDto(account));
            }
        }
        return accountsDto;
    }

    public AccountDto createAccount(AccountDto dto) {
        Account cbu = repository.findByCbu(dto.getCbu());
        Account alias = repository.findByAlias(dto.getAlias());
        if(alias == null && cbu == null){
            dto.setDeleted(false);
            dto.setTransfers(new ArrayList<Transfer>());
            dto.setCreated_at(LocalDateTime.now());
            dto.setUpdated_at(LocalDateTime.now());
            Account newAccount = AccountMapper.dtoToAccount(dto);
            return AccountMapper.accountToDto(repository.save(newAccount));
        }
        return null;
    }

    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (repository.existsById(id)){
            Account acc =  repository.findById(id).get();

            if (dto.getAlias() != null){
                acc.setAlias(dto.getAlias());
            }
            if (dto.getAmount() != null){
                acc.setAmount(dto.getAmount());
            }
            repository.save(acc);
            return AccountMapper.accountToDto(acc);

        } else {
            return null;
        }
    }

    public String deleteAccount(Long id) {
        if (repository.existsById(id)){
            Account accountToDeleted = repository.findById(id).get();
            if(accountToDeleted.getDeleted() == false){
                accountToDeleted.setDeleted(true);
                repository.save(accountToDeleted);
            }
            return "Cuenta eliminada";
        } else {
            return "No se pudo eliminar la cuenta";
        }
    }

}
