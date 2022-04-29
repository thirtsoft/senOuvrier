package com.ouvriers.repository;

import com.ouvriers.models.HistoriqueAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HistoriqueAnnonceRepository extends JpaRepository<HistoriqueAnnonce, Long> {

    List<HistoriqueAnnonce> findHistoriqueAnnonceByOrderByIdDesc();
}
