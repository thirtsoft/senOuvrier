package com.ouvriers.repository;

import com.ouvriers.models.Metier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Long> {
}
