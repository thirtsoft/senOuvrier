package com.ouvriers.services;

import com.ouvriers.dtos.AddresseDto;
import com.ouvriers.dtos.RecruteurDto;

import java.math.BigDecimal;
import java.util.List;

public interface RecruteurService {

    RecruteurDto save(RecruteurDto recruteurDto);

    RecruteurDto update(Long idRecruteur, RecruteurDto recruteurDto);

    RecruteurDto findById(Long id);

    List<RecruteurDto> findAll();

    void delete(Long id);

    BigDecimal countNumbersOfRecruteurs();

}
