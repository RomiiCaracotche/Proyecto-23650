package com.ar.cac.tpfinal.repositories;

import com.ar.cac.tpfinal.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByCbu(String cbu);
    Account findByAlias(String alias);
    Boolean existsByCbu(String cbu);
    Boolean existsByAlias(String alias);
}
