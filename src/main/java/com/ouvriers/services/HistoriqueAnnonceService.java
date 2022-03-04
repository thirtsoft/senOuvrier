package com.ouvriers.services;

import com.ouvriers.dtos.HistoriqueAnnonceDto;

import java.math.BigDecimal;
import java.util.List;

public interface HistoriqueAnnonceService {

    HistoriqueAnnonceDto save(HistoriqueAnnonceDto historiqueAnnonceDto);

    HistoriqueAnnonceDto update(Long id, HistoriqueAnnonceDto historiqueAnnonceDto);

    HistoriqueAnnonceDto findById(Long id);

    List<HistoriqueAnnonceDto> findAll();

    List<HistoriqueAnnonceDto> findHistoriqueAnnonceByOrderByIdDesc();

    BigDecimal countNumbersOfHistoriqueAnnonces();

    void delete(Long id);
}
