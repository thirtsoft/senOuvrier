package com.ouvriers.services;

import com.ouvriers.dtos.HistoriqueLoginDto;

import java.math.BigDecimal;
import java.util.List;

public interface HistoriqueLoginService {

    HistoriqueLoginDto save(HistoriqueLoginDto historiqueLoginDto);

    HistoriqueLoginDto update(Long idLogin, HistoriqueLoginDto historiqueLoginDto);

    HistoriqueLoginDto findById(Long id);

    List<HistoriqueLoginDto> findAll();

    List<HistoriqueLoginDto> findHistoriqueLoginByOrderByIdDesc();

    BigDecimal countNumbersOfHistoriqueLogins();

    void delete(Long id);

}
