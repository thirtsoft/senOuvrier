package com.ouvriers.services;

import com.ouvriers.dtos.TarifDto;

import java.util.List;

public interface TarifService {

    TarifDto save(TarifDto tarifDto);

    TarifDto update(Long idTarif, TarifDto tarifDto);

    TarifDto findById(Long id);

    List<TarifDto> findAll();

    void delete(Long id);

}
