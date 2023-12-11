package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.InvestmentDto;
import com.ar.cac.tpfinal.entities.Account;
import com.ar.cac.tpfinal.entities.Investment;
import com.ar.cac.tpfinal.mappers.InvestmentMapper;
import com.ar.cac.tpfinal.repositories.AccountRepository;
import com.ar.cac.tpfinal.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentService {
    private InvestmentRepository repository;
    private AccountRepository accountRepository;

    private InvestmentService(InvestmentRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    public List<InvestmentDto> getInvestments(){
        List<Investment> investments = repository.findAll();
        List<InvestmentDto> investmentsDto = new ArrayList<InvestmentDto>();
        for (Investment investment: investments) {
            investmentsDto.add(InvestmentMapper.investmentToDto(investment));
        }
        return investmentsDto;
    }

    public InvestmentDto getInvestmentById(Long id) {
        Investment investment = repository.findById(id).get();
        return InvestmentMapper.investmentToDto(investment);
    }

    public List<InvestmentDto> getInvestmentByCbu(String cbu) {
        List<Investment> investments = repository.findAllByCbu(cbu);
        List<InvestmentDto> investmentDto = new ArrayList<>();
        for (Investment investment: investments){
            investmentDto.add(InvestmentMapper.investmentToDto(investment));
        }
        return investmentDto;
    }

    public InvestmentDto createInvestment(InvestmentDto dto) {
        Account account = accountRepository.findByCbu(dto.getCbu());
        if(account != null) {
            double revenue = dto.getAmount()+(((dto.getAmount()*dto.getPercentage()))/100)*(double)dto.getDuration();
            dto.setRevenue(revenue);
            dto.setStart_date(LocalDateTime.now());
            dto.setFinish_date(LocalDateTime.now().plusMonths(dto.getDuration()));
            Investment investment = InvestmentMapper.dtoToInvestment(dto);
            return InvestmentMapper.investmentToDto(repository.save(investment));
        }
        return null;
    }

    public String deleteInvestment(Long id){
        if (repository.existsById(id)){
            Investment inv = repository.findById(id).get();
            repository.delete(inv);
            return "El investment con id: " + id + " ha sido eliminado correctamente.";
        }
        return "El investment con id: " + id + ", no existe.";
    }

}
