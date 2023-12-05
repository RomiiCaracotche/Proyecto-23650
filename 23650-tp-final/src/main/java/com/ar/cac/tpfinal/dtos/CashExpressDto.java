package com.ar.cac.tpfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashExpressDto {

    private Long id;

    private String nombre;

    private String direccion;

    private String cbu;

    private BigDecimal monto;

    private LocalDateTime fecha;
}
