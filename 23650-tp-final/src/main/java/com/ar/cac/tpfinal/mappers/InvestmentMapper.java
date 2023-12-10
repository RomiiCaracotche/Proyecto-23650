package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.dtos.InvestmentDto;
import com.ar.cac.tpfinal.entities.Investment;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class InvestmentMapper {

    public static Investment dtoToInvestment(InvestmentDto dto){

        Investment inv = new Investment();
        inv.setCbu(dto.getCbu());
        inv.setPercentage(dto.getPercentage());
        inv.setDuration(dto.getDuration());
        inv.setId(dto.getId());
        inv.setStart_date(dto.getStart_date());
        inv.setFinish_date(dto.getFinish_date());
        inv.setAmount(dto.getAmount());
        inv.setRevenue(dto.getRevenue());
        return inv;
    }

    public static InvestmentDto investmentToDto(Investment inv){

        InvestmentDto dto = new InvestmentDto();
        dto.setId(inv.getId());
        dto.setCbu(inv.getCbu());
        dto.setPercentage(inv.getPercentage());
        dto.setDuration(inv.getDuration());
        dto.setStart_date(inv.getStart_date());
        dto.setFinish_date(inv.getFinish_date());
        dto.setAmount(inv.getAmount());
        dto.setRevenue(inv.getRevenue());
        return dto;
    }
}
