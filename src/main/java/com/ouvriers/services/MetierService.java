package com.ouvriers.services;

import com.ouvriers.dtos.MetierDto;

import java.math.BigDecimal;
import java.util.List;

public interface MetierService {

    MetierDto save(MetierDto metierDto);

    MetierDto update(Long idMetier, MetierDto metierDto);

    MetierDto findById(Long id);

    MetierDto findByReference(String reference);

    MetierDto findByDesignation(String designation);

    List<MetierDto> findAll();

    List<MetierDto> findListMetierByReference(String keyword);

    List<MetierDto> findByMetierByIdDesc();

    BigDecimal countNumbersOfMetiers();

    void delete(Long id);

}
