package com.ouvriers.services;

import com.ouvriers.dtos.AddresseDto;

import java.util.List;

public interface RoleService {

    AddresseDto save(AddresseDto addresseDto);

    AddresseDto update(Long idAddress, AddresseDto addresseDto);

    AddresseDto findById(Long id);

    List<AddresseDto> findAll();

    void delete(Long id);

}
