package com.ouvriers.services;

import com.ouvriers.dtos.TarifDto;
import com.ouvriers.dtos.VilleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VilleService {

    VilleDto save(VilleDto villeDto);

    VilleDto update(Long idVille, VilleDto villeDto);

    VilleDto findById(Long id);

    List<VilleDto> findAll();

    void delete(Long id);


}
