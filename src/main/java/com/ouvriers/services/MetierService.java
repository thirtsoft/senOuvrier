package com.ouvriers.services;

import com.ouvriers.dtos.MetierDto;

import java.util.List;

public interface MetierService {

    MetierDto save(MetierDto metierDto);

    MetierDto update(Long idMetier, MetierDto metierDto);

    MetierDto findById(Long id);

    List<MetierDto> findAll();

    void delete(Long id);

}
