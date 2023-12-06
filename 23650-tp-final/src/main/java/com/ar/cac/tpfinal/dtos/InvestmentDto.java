package com.ar.cac.tpfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentDto {
    private Long id;

    private double percentage;

    private int duration;

    private double revenue;

    private LocalDateTime start_date;

    private double amount;

    private String cbu;

    private LocalDateTime finish_date;

}
