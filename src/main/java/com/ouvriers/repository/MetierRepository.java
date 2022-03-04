package com.ouvriers.repository;

import com.ouvriers.models.Metier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Long> {

    Optional<Metier> findByReference(String reference);

    Optional<Metier> findByDesignation(String designation);

    @Query("select art from Metier art where art.reference like :y")
    List<Metier> findListMetierByReference(@Param("y") String reference);

    List<Metier> findListOfMetierByOrderByIdDesc();

    @Query("select count(p) from Metier p ")
    BigDecimal countNumberOfMetiers();

    @Query("select p from Metier p")
    Page<Metier> findMetierByPageable(Pageable pageable);


}
