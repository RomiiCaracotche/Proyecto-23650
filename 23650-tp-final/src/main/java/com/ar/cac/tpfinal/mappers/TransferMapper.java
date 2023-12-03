package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.dtos.TransferDto;
import com.ar.cac.tpfinal.entities.Transfer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    //PATRON BUILDER: permite crear los objetos sin definir un constructor y setearle los atributos necesarios
    public Transfer dtoToTransfer(TransferDto dto) {
        return Transfer.builder()
                .amount(dto.getAmount())
                .date(dto.getDate())
                .id(dto.getId())
                .id(dto.getId())
                .build();
    }

    public TransferDto TransferToDto(Transfer transfer) {
        //agregar id
        return null;
    }

}
