package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.AccountDto;
import com.ar.cac.tpfinal.dtos.UserDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.Transfer;
import com.ar.cac.tpfinal.entities.User;
import com.ar.cac.tpfinal.mappers.AccountMapper;
import com.ar.cac.tpfinal.mappers.UserMapper;
import com.ar.cac.tpfinal.repositories.AccountRepository;
import com.ar.cac.tpfinal.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    private final AccountRepository repository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
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
        User user = userRepository.findById(dto.getOwner().getId()).get();
        if(alias == null && cbu == null) {
            dto.setDeleted(false);
            dto.setOwner(user);
            dto.setCbu(cbuGenerator(user.getDni(), user.getAccounts().size()));
            dto.setAlias(aliasGenerator(user.getUsername(), user.getDni(), user.getAccounts().size()));
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

    public String cbuGenerator(String dni, Integer cantDeCuetas){
        String generatedCbu = Integer.toString(cantDeCuetas) + dni;
        Random random = new Random();
        // Se itera las veces necesarias para completar los 22 dígitos de un CBU
        // quedando números random + cantidad de cuentas + dni
        for(int i = 0; i < (22 - dni.length()); i++){
            generatedCbu = Integer.toString(random.nextInt(10)) + generatedCbu;
        }
        return generatedCbu;
    }

    public String aliasGenerator(String username, String dni, Integer cantDeCuetas){
        // Se normaliza pasando a minúsculas, quitando los puntos existentes
        // y reemplazando los espacios por puntos. Para que sea única se agrega la cantidad de cuentas como prefijo
        // al número de DNI, numero que se actualiza cada vez que se crea una cuenta.
        return (Integer.toString(cantDeCuetas) + "." + username.toLowerCase().replace(".", "").replace(" ", ".") + "." + dni);

    }

}