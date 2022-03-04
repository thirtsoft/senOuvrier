package com.ouvriers.services;

import com.ouvriers.dtos.VilleDto;

import java.util.List;

public interface VilleService {

    VilleDto save(VilleDto villeDto);

    VilleDto update(Long idVille, VilleDto villeDto);

    VilleDto findById(Long id);

    List<VilleDto> findAll();

    List<VilleDto> findByVillesByIdDesc();

    void delete(Long id);


}
