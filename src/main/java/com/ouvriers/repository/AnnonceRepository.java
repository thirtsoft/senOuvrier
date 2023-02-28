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

    @Query("select count(p) from Annonce p ")
    BigDecimal countNumberOfAnnonces();

    @Query("select count(c) from Annonce c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfAnnoncesInMonth();

    @Query("select count(c) from Annonce c where c.status = 'ENCOURS' ")
    BigDecimal countNumberOfAnnonceByStatusPending();

    @Query("select count(c) from Annonce c where c.status = 'VALID' ")
    BigDecimal countNumberOfAnnonceByStatusValidated();

    @Query("select count(c) from Annonce c where c.status = 'REFUSED' ")
    BigDecimal countNumberOfAnnonceByStatusRefused();

    List<Annonce> findByOrderByIdDesc();

    @Query("select art from Annonce art where art.selected = true")
    List<Annonce> findAnnonceBySelected();

    @Query("select annnonce from Annonce annnonce where annnonce.reference like :x")
    List<Annonce> findAnnonceByKeyword(@Param("x") String mc);

    @Query("select art from Annonce art where art.libelle like :y")
    List<Annonce> findListAnnonceByLibelle(@Param("y") String libelle);

    @Query("select p from Annonce p where p.metier.id =:mId")
    List<Annonce> findListAnnonceByMetier(@Param("mId") Long metierId);

    List<Annonce> findTop8ByOrderByIdDesc();

    @Query("select p from Annonce p where p.utilisateur.id =:user")
    Optional<Annonce> FindAnnonceByCustomerId(@Param("user") Long userId);

    @Query("select p from Annonce p where p.utilisateur.id =:user order by id Desc")
    List<Annonce> FindListAnnonceByCustomerId(@Param("user") Long userId);

    @Query("select c from Annonce c where c.status = 'ENCOURS' order by id Desc ")
    List<Annonce> findListAnnonceByStatusPending();

    @Query("select c from Annonce c where c.status = 'VALID' order by id Desc ")
    List<Annonce> findListAnnonceByStatusValid();

    @Query("select c from Annonce c where c.status = 'REFUSED' order by id Desc ")
    List<Annonce> findListAnnonceByStatusRefused();

    @Query("select EXTRACT(month from(c.createdDate)), count(c) from Annonce c group by EXTRACT(month from(c.createdDate))")
    List<?> countNumberOfAnnonceByMonth();

    @Query("select EXTRACT(year from(c.createdDate)), count(c) from Annonce c group by EXTRACT(year from(c.createdDate))")
    List<?> countNumberOfAnnonceByYear();

    @Query("select p from Annonce p")
    Page<Annonce> findAnnonceByPageable(Pageable pageable);

    @Query("select annonce from Annonce annonce where annonce.metier.id =:metierId")
    Page<Annonce> findAnnonceByMetierPageables(@Param("metierId") Long metierId, Pageable pageable);
}
