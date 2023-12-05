package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.TransferDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.Transfer;
import com.ar.cac.tpfinal.repositories.AccountRepository;
import com.ar.cac.tpfinal.repositories.TransferRepository;
import com.ar.cac.tpfinal.mappers.TransferMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {

    private final TransferRepository repository;
    private final AccountRepository accountRepository;

    public TransferService(TransferRepository repository, AccountRepository accountRepository){
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    public List<TransferDto> getTransfers(){
        List<Transfer> transfers = repository.findAll();
        List<TransferDto> TransfersDto = new ArrayList<TransferDto>();
        for (Transfer transfer: transfers) {
            TransfersDto.add(TransferMapper.transferToDto(transfer));
        }
        return TransfersDto;
    }

    public TransferDto getTransferById(Long id){
        Transfer transfer = repository.findById(id).get();
        return TransferMapper.transferToDto(transfer);
    }

    @Transactional
    public TransferDto createTransfer(TransferDto dto) {
        // Comprobar si ambas cuentas existen
        Account originAccount = accountRepository.findById(dto.getOrigin()).get();
        Account targetAccount = accountRepository.findById(dto.getTarget()).get();

        // Comprobar si la cuenta tiene saldo
        if (originAccount.getAmount().compareTo(dto.getAmount()) > 0) {
            originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
            targetAccount.setAmount(targetAccount.getAmount().add(dto.getAmount()));

            accountRepository.save(originAccount);
            accountRepository.save(targetAccount);

            Transfer transfer = new Transfer();
            transfer.setDate(LocalDateTime.now());
            transfer.setOrigin(originAccount.getId());
            transfer.setTarget(targetAccount.getId());
            transfer.setAmount(dto.getAmount());
            transfer = repository.save(transfer);

            return TransferMapper.transferToDto(transfer);
        }
        return null;
    }

}
