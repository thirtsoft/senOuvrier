package com.ouvriers.repository;

import com.ouvriers.models.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {

    @Query("select count(p) from Recruteur p ")
    BigDecimal countNumberOfRecruteurs();
}
