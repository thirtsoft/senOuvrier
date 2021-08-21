package com.ouvriers.repository;

import com.ouvriers.models.Ouvrier;
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
public interface OuvrierRepository extends JpaRepository<Ouvrier, Long> {

    Optional<Ouvrier> findByReference(String reference);

    Optional<Ouvrier> findByDisponibility(String disponibility);

    @Query("select count(p) from Ouvrier p ")
    BigDecimal countNumberOfOuvriers();

    @Query("select p from Ouvrier p where p.metier.id =:mId")
    List<Ouvrier> findListOuvriersByMetier(@Param("mId") Long metierId);

    @Query("select art from Ouvrier art where art.reference like :x")
    List<Ouvrier> findListOfOuvriersByKeyword(@Param("x") String mc);

    @Query("select ouv from Ouvrier ouv where ouv.disponibity like :z")
    List<Ouvrier> findListOfOuvrierByDisponibility(@Param("z") String disponibity);

    @Query("select p from Ouvrier p")
    Page<Ouvrier> findOuvriersByPageable(Pageable pageable);

    @Query("select ouv from Ouvrier ouv where ouv.disponibity like :dispo")
    Page<Ouvrier> findOuvriersByKeywordByPageable(@Param("dispo") String mc, Pageable pageable);

    @Query("select ouv from Ouvrier ouv where ouv.addresse.id =:add")
    Page<Ouvrier> findOuvriersByLocalityPageables(@Param("add") Long addId, Pageable pageable);

    @Query("select ouv from Ouvrier ouv where ouv.metier.id =:metierId")
    Page<Ouvrier> findOuvriersByMetierPageables(@Param("metierId") Long metierId, Pageable pageable);


}
