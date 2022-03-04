package com.ouvriers.services;

import com.ouvriers.dtos.JetonDto;

import java.math.BigDecimal;
import java.util.List;

public interface JetonService {

    JetonDto save(JetonDto jetonDto);

    JetonDto update(Long id, JetonDto jetonDto);

    JetonDto findById(Long id);

    List<JetonDto> findAll();

    List<JetonDto> findAllJetonsByOrderByIdDesc();

    BigDecimal countNumbersOfJetons();

    void delete(Long id);

}
