package com.ouvriers.repository;

import com.ouvriers.models.Addresse;
import com.ouvriers.models.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {
}
