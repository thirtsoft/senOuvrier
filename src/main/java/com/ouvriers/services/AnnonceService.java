package com.ouvriers.services;

import com.ouvriers.models.Annonce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface AnnonceService {

    Annonce save(Annonce annonce);

    Annonce update(Long idAnnonce, Annonce annonce);

    Annonce findById(Long id);

    Annonce findByReference(String reference);

    Annonce FindAnnonceByCustomerId(Long userId);

    BigDecimal countNumbersOfAnnonces();

    BigDecimal countNumberOfAnnoncesInMonth();

    BigDecimal countNumberOfAnnonceByStatusPending();

    List<Annonce> findAll();

    List<Annonce> findByAnnonceByIdDesc();

    List<Annonce> findListAnnonceBySelected();

    List<Annonce> findListAnnonceByKeyword(String keyword);

    List<Annonce> findListAnnonceByLibelle(String libelle);

    List<Annonce> findListAnnonceByMetier(Long pId);

    List<Annonce> FindListAnnonceByCustomerId(Long userId);

    List<Annonce> find5LatestRecordsByOrderByIdDesc();

    List<Annonce> findListAnnonceByStatusPending();

    List<Annonce> findListAnnonceByStatusValid();

    List<Annonce> findListAnnonceByStatusRejet();

    List<?> countNumberTotalOfAnnonceByMonth();

    List<?> countNumberTotalOfAnnonceByYear();

    Page<Annonce> findAnnonceByPageable(Pageable pageable);

    Page<Annonce> findAnnonceByMetierByPageable(Long metierId, Pageable pageable);

    void delete(Long id);


}
