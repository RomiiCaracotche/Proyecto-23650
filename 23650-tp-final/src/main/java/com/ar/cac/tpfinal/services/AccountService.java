package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.dtos.UserDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.Enums.AccountType;
import com.ar.cac.tpfinal.mappers.AccountMapper;
import com.ar.cac.tpfinal.mappers.UserMapper;
import com.ar.cac.tpfinal.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository repository;
    private final UserService userService;

    private AccountService(AccountRepository repository, UserService userService){
        this.repository = repository;
        this.userService = userService;
    }

    public AccountDto getAccountById(Long id) {
        Account account = repository.findById(id).get();
        return AccountMapper.accountToDto(account);
    }

    public List<AccountDto> getAccounts() {
        return repository.findAll().stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public AccountDto createAccount(AccountDto dto) {
        dto.setAmount(BigDecimal.ZERO);
        // TODO: REFACTOR para crear diferentes tipos de cuenta inicial
        // TODO: REFACTOR para modificar created_at y updated_at
        dto.setType(AccountType.CAJA_AHORRO_PESOS);

        Account newAccount = AccountMapper.dtoToAccount(dto);
        return AccountMapper.accountToDto(repository.save(newAccount));
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
