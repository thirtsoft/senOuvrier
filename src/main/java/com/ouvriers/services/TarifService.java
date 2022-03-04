package com.ouvriers.services;

import com.ouvriers.dtos.TarifDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TarifService {

    TarifDto save(TarifDto tarifDto);

    TarifDto update(Long idTarif, TarifDto tarifDto);

    TarifDto findById(Long id);

    List<TarifDto> findAll();

    List<TarifDto> findByTarifByIdDesc();

    List<TarifDto> findListTarifDtoByKeyword(String keyword);

    List<TarifDto> findListTarifDtoByAnnonce(Long pId);

    Page<TarifDto> findTarifByPageable(Pageable pageable);

    Page<TarifDto> findTarifByAnnonceByPageable(Long annonceId, Pageable pageable);

    void delete(Long id);

}
