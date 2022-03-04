package com.ouvriers.services;

import com.ouvriers.dtos.AddresseDto;
import com.ouvriers.dtos.AnnonceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface AnnonceService {

    AnnonceDto save(AnnonceDto annonceDto);

    AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto);

    AnnonceDto findById(Long id);

    AnnonceDto findByReference(String reference);

    AnnonceDto FindAnnonceByCustomerId(Long userId);

    BigDecimal countNumbersOfAnnonces();

    BigDecimal countNumberOfAnnoncesInMonth();

    BigDecimal countNumberOfAnnonceByStatusPending();

    List<AnnonceDto> findAll();

    List<AnnonceDto> findByAnnonceByIdDesc();

    List<AnnonceDto> findListAnnonceBySelected();

    List<AnnonceDto> findListAnnonceByKeyword(String keyword);

    List<AnnonceDto> findListAnnonceByLibelle(String libelle);

    List<AnnonceDto> findListAnnonceByMetier(Long pId);

    List<AnnonceDto> FindListAnnonceByCustomerId(Long userId);

    List<AnnonceDto> find5LatestRecordsByOrderByIdDesc();

    List<AnnonceDto> findListAnnonceByStatusPending();

    List<AnnonceDto> findListAnnonceByStatusValid();

    List<AnnonceDto> findListAnnonceByStatusRejet();

    List<?> countNumberTotalOfAnnonceByMonth();

    List<?> countNumberTotalOfAnnonceByYear();

    Page<AnnonceDto> findAnnonceByPageable(Pageable pageable);

    Page<AnnonceDto> findAnnonceByMetierByPageable(Long metierId, Pageable pageable);

    void delete(Long id);


}
