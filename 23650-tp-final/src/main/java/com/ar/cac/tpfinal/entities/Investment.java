package com.ar.cac.tpfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "inversiones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "porcentaje")
    private double percentage;

    @Column(name = "duracion")
    private int duration;

    @Column(name = "fecha_inicio")
    private LocalDateTime start_date;

    private double revenue;

    @Column(name = "monto")
    private double amount;

    private String cbu;

    @Column(name = "fecha_finalizacion")
    private LocalDateTime finish_date;
}
