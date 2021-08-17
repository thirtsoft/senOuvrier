package com.ouvriers.repository;

import com.ouvriers.models.Addresse;
import com.ouvriers.models.Ouvrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuvrierRepository extends JpaRepository<Ouvrier, Long> {
}
