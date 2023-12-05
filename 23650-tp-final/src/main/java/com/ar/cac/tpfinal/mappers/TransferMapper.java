package com.ar.cac.tpfinal.mappers;

import com.ar.cac.tpfinal.dtos.TransferDto;
import com.ar.cac.tpfinal.entities.Transfer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    public Transfer dtoToTransfer(TransferDto dto){
        return Transfer.builder()
                .amount(dto.getAmount())
                .date(dto.getDate())
                .origin(dto.getOrigin())
                .target(dto.getTarget())
                .build();
    }

    public TransferDto transferToDto(Transfer transfer){
        return TransferDto.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .target(transfer.getTarget())
                .origin(transfer.getOrigin())
                .date(transfer.getDate())
                .build();
    }

}
