package com.ar.cac.tpfinal.dtos;

import com.ar.cac.tpfinal.entities.Enums.AccountType;
import com.ar.cac.tpfinal.entities.Transfer;
import com.ar.cac.tpfinal.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private Long referencia;

    private AccountType type;

    private String cbu;

    private String alias;

    private BigDecimal amount;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private User owner;

    private List<Transfer> transfers;

}
