package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.dtos.CashExpressDto;
import com.ar.cac.tpfinal.entities.CashExpress;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CashExpressMapper {

    public static CashExpress dtoToCash(CashExpressDto dto){

        CashExpress cash = new CashExpress();
        cash.setCbu(dto.getCbu());
        cash.setDireccion(dto.getDireccion());
        cash.setFecha(dto.getFecha());
        cash.setMonto(dto.getMonto());
        cash.setNombre(dto.getNombre());

        return cash;
    }

    public static CashExpressDto cashToDto(CashExpress cash){

        CashExpressDto dto = new CashExpressDto();
        dto.setCbu(cash.getCbu());
        dto.setDireccion(cash.getDireccion());
        dto.setFecha(cash.getFecha());
        dto.setMonto(cash.getMonto());
        dto.setNombre(cash.getNombre());

        return dto;
    }

}
