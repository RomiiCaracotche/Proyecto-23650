package com.ar.cac.tpfinal.services;

import com.ar.cac.tpfinal.dtos.TransferDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferService {

    public List<TransferDto> getTransfers() {
        return null;
    }
    public TransferDto getTransferById(Long id) {
        return null;
    }
    @Transactional
    public TransferDto createTransfer(TransferDto transfer) {
        //verificar si las dos cuentas existen

        //comprobar si la cuenta de origen tiene fondos

        //si tiene fondos realizo la transferencia

        //actualizar las cuentas

        //crear la transferencia y guardarla en la bbdd

        return null;
    }

}
