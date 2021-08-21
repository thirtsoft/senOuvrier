package com.ouvriers.repository;

import com.ouvriers.models.Annonce;
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
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    Optional<Annonce> findByReference(String reference);

    @Query("select annnonce from Annonce art where annnonce.reference like :x")
    List<Annonce> findAnnonceByKeyword(@Param("x") String mc);

    @Query("select art from Annonce art where art.libelle like :y")
    List<Annonce> findListAnnonceByLibelle(@Param("y") String libelle);

    @Query("select p from Annonce p where p.metier.id =:mId")
    List<Annonce> findListAnnonceByMetier(@Param("mId") Long metierId);

    @Query("select count(p) from Annonce p ")
    BigDecimal countNumberOfAnnonces();

    @Query("select p from Annonce p")
    Page<Annonce> findAnnonceByPageable(Pageable pageable);

    @Query("select annonce from Annonce annonce where annonce.metier.id =:metierId")
    Page<Annonce> findAnnonceByMetierPageables(@Param("metierId") Long metierId, Pageable pageable);
}
