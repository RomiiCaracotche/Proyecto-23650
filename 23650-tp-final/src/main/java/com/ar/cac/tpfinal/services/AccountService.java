package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.Transfer;
import com.ar.cac.tpfinal.mappers.AccountMapper;
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
        try {
            List<Account> accounts = repository.findAll();
            List<AccountDto> accountDto = new ArrayList<AccountDto>();
            for (Account account: accounts) {
                if(account.getDeleted() == false) {
                    accountDto.add(AccountMapper.accountToDto(account));
                }
            }
            return accountDto;
        } catch (Exception e) {
            System.out.println("aca esta el error");
        }
        return null;
        //return repository.findAll().stream()
        //        .map(AccountMapper::accountToDto)
        //        .collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id) {
        Account account = repository.findById(id).get();
        return AccountMapper.accountToDto(account);
    }

    public AccountDto createAccount(AccountDto dto) {
        //por dto me viene type y alias
        //String cbuGenerator = Long.toString(1000000000L + (long) (Math.random() * (9999999999L - 1000000000L)));
        //dto.setCbu(cbuGenerator);
        //dto.setCbu("00000000000000000000001");
        //Account cbu = repository.findByCbu("00000000000000000000001");
        //Account alias = repository.findByAlias(dto.getAlias());
        //if(cbu == null) {
            dto.setAmount(BigDecimal.ZERO);
            dto.setDeleted(false);
            dto.setTransfers(new ArrayList<Transfer>());
            dto.setCreated_at(LocalDateTime.now());
            dto.setUpdated_at(LocalDateTime.now());
            Account newAccount = AccountMapper.dtoToAccount(dto);
        return AccountMapper.accountToDto(repository.save(newAccount));
        //}
        //return null;
    }

    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (repository.existsById(id)){
            Account acc =  repository.findById(id).get();

            if (dto.getAlias() != null){
                acc.setAlias(dto.getAlias());
            }
            if (dto.getType() != null){
                acc.setType(dto.getType());
            }
            if (dto.getCbu() != null){
                acc.setCbu(dto.getCbu());
            }
            if (dto.getAmount() != null){
                acc.setAmount(dto.getAmount());
            }
            return AccountMapper.accountToDto(acc);

        } else {
            return null;
        }
    }

    // TODO: hacer la baja logica!!!
    public String deleteAccount(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Cuenta eliminada";
        } else {
            return "No se pudo eliminar la cuenta";
        }
    }

}
