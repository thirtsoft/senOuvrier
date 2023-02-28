package com.ouvriers.repository;

import com.ouvriers.models.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

    List<Ville> findVilleByOrderByIdDesc();

}
