package com.ouvriers.repository;

import com.ouvriers.models.Tarif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {

    List<Tarif> findTarifByOrderByIdDesc();

    @Query("select taf from Tarif taf where taf.reference like :x")
    List<Tarif> findTarifByKeyword(@Param("x") String mc);

    @Query("select taf from Tarif taf where taf.typeAnnonce.id =:pId")
    List<Tarif> findTarifByAnnonce(@Param("pId") Long annonceId);

    @Query("select taf from Tarif taf")
    Page<Tarif> findTarif(Pageable pageable);

    @Query("select taf from Tarif taf where taf.typeAnnonce.id =:annonceId")
    Page<Tarif> findTarifByAnnoncePageables(@Param("annonceId") Long annonceId, Pageable pageable);



}
