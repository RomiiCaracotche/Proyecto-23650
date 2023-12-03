package com.ar.cac.tpfinal.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_origin;

    private Long id_target;

    @Column(name = "fecha")
    private LocalDateTime date;

    @Column(name = "monto")
    private BigDecimal amount;

    @Column(name = "eliminado")
    private String deleted;

}
