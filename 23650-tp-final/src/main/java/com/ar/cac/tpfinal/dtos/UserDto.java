package com.ar.cac.tpfinal.dtos;

import com.ar.cac.tpfinal.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String dni;

    private String address;

    private LocalDate birthday_date;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private List<Account> accounts;

    private Boolean deleted;
}
