package com.ouvriers.repository;

import com.ouvriers.models.Addresse;
import com.ouvriers.models.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {
}
