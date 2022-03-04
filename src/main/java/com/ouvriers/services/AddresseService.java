package com.ouvriers.services;

import com.ouvriers.dtos.AddresseDto;

import java.util.List;

public interface AddresseService {

    AddresseDto save(AddresseDto addresseDto);

    AddresseDto update(Long idAddress, AddresseDto addresseDto);

    AddresseDto findById(Long id);

    List<AddresseDto> findAll();

    List<AddresseDto> findByAddresseByIdDesc();

    void delete(Long id);

}
