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

    List<AnnonceDto> findAll();

    List<AnnonceDto> findListAnnonceByKeyword(String keyword);

    List<AnnonceDto> findListAnnonceByLibelle(String libelle);

    List<AnnonceDto> findListAnnonceByPermis(Long pId);

    BigDecimal countNumbersOfAnnonces();

    Page<AnnonceDto> findAnnonceByPageable(Pageable pageable);

    Page<AnnonceDto> findAnnonceByPermisByPageable(Long permisId, Pageable pageable);



    void delete(Long id);


}
