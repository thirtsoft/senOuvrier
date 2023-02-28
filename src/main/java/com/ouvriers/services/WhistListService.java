package com.ouvriers.services;

import com.ouvriers.models.WhistList;

import java.math.BigDecimal;
import java.util.List;

public interface WhistListService {

    WhistList save(WhistList WhistList);

    WhistList saveNoteToOuvrier(Long idOuv, WhistList WhistList);

    WhistList update(Long idNotification, WhistList WhistList);

    WhistList findById(Long id);

    List<WhistList> findAll();

    List<WhistList> findTop3RatingOrderByCreatedDateDesc();

    List<WhistList> findByOrderByIdDesc();

    List<WhistList> findTop4ByOrderByCreatedDateDescByOuvrierId(String ouvRef);

    BigDecimal countNumberOfNotificationByOuvrierId(String ouvRef);

    BigDecimal countNumberOfNotification();

    void delete(Long id);


}
