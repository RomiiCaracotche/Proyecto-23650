package com.ar.cac.tpfinal.repositories;

import com.ar.cac.tpfinal.entities.CashExpress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashExpressRepository extends JpaRepository<CashExpress, Long> {
}
