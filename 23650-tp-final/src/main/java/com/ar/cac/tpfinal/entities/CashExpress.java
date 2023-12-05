package com.ar.cac.tpfinal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashExpress {
    private Long id;
    private String nombre;
    private String direccion;
    private String cbu;
    private BigDecimal monto;
    private LocalDateTime fecha;
}
