package com.ar.cac.tpfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_express")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashExpress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "banco")
    private String name;

    @Column(name = "direccion")
    private String address;

    private String cbu;

    @Column(name = "monto")
    private BigDecimal amount;

    @Column(name = "fecha")
    private LocalDateTime date;

    @Column(name = "operacion")
    private String type;

}
