package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.dtos.CashExpressDto;
import com.ar.cac.tpfinal.entities.CashExpress;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CashExpressMapper {

    public static CashExpress dtoToCash(CashExpressDto dto){

        CashExpress cash = new CashExpress();
        cash.setCbu(dto.getCbu());
        cash.setAddress(dto.getAddress());
        cash.setDate(dto.getDate());
        cash.setAmount(dto.getAmount());
        cash.setName(dto.getName());
        cash.setType(dto.getType());
        return cash;
    }

    public static CashExpressDto cashToDto(CashExpress cash){

        CashExpressDto dto = new CashExpressDto();
        dto.setCbu(cash.getCbu());
        dto.setAddress(cash.getAddress());
        dto.setDate(cash.getDate());
        dto.setAmount(cash.getAmount());
        dto.setName(cash.getName());
        dto.setType(cash.getType());
        return dto;
    }

}
