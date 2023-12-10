package com.ar.cac.tpfinal.repositories;

import com.ar.cac.tpfinal.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment,Long> {

    Investment findByCbu(String Cbu);

}
