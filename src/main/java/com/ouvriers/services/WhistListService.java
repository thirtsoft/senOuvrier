package com.ouvriers.services;

import com.ouvriers.dtos.WhistListDto;

import java.util.List;

public interface WhistListService {

    WhistListDto save(WhistListDto whistListDto);

    WhistListDto update(Long idVille, WhistListDto whistListDto);

    WhistListDto findById(Long id);

    List<WhistListDto> findAll();

    void delete(Long id);


}
