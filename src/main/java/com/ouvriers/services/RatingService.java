package com.ouvriers.services;

import com.ouvriers.models.HistoriqueLogin;
import com.ouvriers.models.Rating;
import org.springframework.data.repository.query.Param;

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

    List<Rating> FindListRatingByCustomerId(Long userId);

    List<Rating> findTop30RatingsOrderByCreatedDateDesc();

    BigDecimal countNumberOfNotificationByOuvrierId(Long idOuv);

    BigDecimal countNumberOfRating();

    void delete(Long id);
}
