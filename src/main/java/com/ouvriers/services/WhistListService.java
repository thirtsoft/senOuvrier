package com.ouvriers.services;

import com.ouvriers.dtos.WhistListDto;

import java.math.BigDecimal;
import java.util.List;

public interface WhistListService {

    WhistListDto save(WhistListDto whistListDto);

    WhistListDto saveNoteToOuvrier(Long idOuv, WhistListDto whistListDto);

    WhistListDto update(Long idNotification, WhistListDto whistListDto);

    WhistListDto findById(Long id);

    List<WhistListDto> findAll();

    List<WhistListDto> findTop3RatingOrderByCreatedDateDesc();

    List<WhistListDto> findByOrderByIdDesc();

    List<WhistListDto> findTop4ByOrderByCreatedDateDescByOuvrierId(String ouvRef);

    BigDecimal countNumberOfNotificationByOuvrierId(String ouvRef);

    BigDecimal countNumberOfNotification();

    void delete(Long id);


}
