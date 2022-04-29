package com.ouvriers.services;

import com.ouvriers.models.Rating;

import java.math.BigDecimal;
import java.util.List;

public interface RatingService {

    Rating save(Rating rating);

    Rating saveNoteToOuvrier(Long idOuv, Rating rating);

    Rating update(Long idRat, Rating rating);

    Rating findById(Long id);

    List<Rating> findAll();

    List<Rating> findTop3RatingOrderByCreatedDateDesc();

    List<Rating> findByOrderByIdDesc();

    List<Rating> findTop4ByOrderByCreatedDateDescByOuvrierId(Long ouvRef);

    BigDecimal countNumberOfNotificationByOuvrierId(String ouvRef);

    BigDecimal countNumberOfRating();

    void delete(Long id);
}
