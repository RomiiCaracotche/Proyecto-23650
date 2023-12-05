package com.ar.cac.tpfinal.dtos;

import com.ar.cac.tpfinal.entities.Account;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDto {

    private Long id;

    private String origin;

    private String target;

    private LocalDateTime date;

    private BigDecimal amount;
}
