package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Rating;
import com.ouvriers.repository.RatingRepository;
import com.ouvriers.services.OuvrierService;
import com.ouvriers.services.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final OuvrierService ouvrierService;


    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository,
                             OuvrierService ouvrierService) {
        this.ratingRepository = ratingRepository;
        this.ouvrierService = ouvrierService;
    }


    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating saveNoteToOuvrier(Long idOuv, Rating rating) {
        Ouvrier ouvrierDtoOptional = ouvrierService.findById(idOuv);

        rating.setOuvrier(ouvrierDtoOptional);

        return ratingRepository.save(rating);

    }

    @Override
    public Rating update(Long idRat, Rating rating) {
        if (!ratingRepository.existsById(idRat)) {
            throw new ResourceNotFoundException("Rating not found");
        }

        Optional<Rating> optionalRating = ratingRepository.findById(idRat);

        if (!optionalRating.isPresent()) {
            throw new ResourceNotFoundException("Rating not found");
        }

        Rating ratingDtoResult = optionalRating.get();
        ratingDtoResult.setNbreEtoile(rating.getNbreEtoile());
        ratingDtoResult.setObservation(rating.getObservation());
        ratingDtoResult.setOuvrier(rating.getOuvrier());

        return ratingRepository.save(ratingDtoResult);

    }

    @Override
    public Rating findById(Long id) {
        if (id == null) {
            log.error("Rating Id is null");
            return null;
        }

        Optional<Rating> optionalRating = ratingRepository.findById(id);

        return Optional.of(optionalRating.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun notification avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> findTop3RatingOrderByCreatedDateDesc() {
        return ratingRepository.findTop3ByOrderByCreatedDateDesc();
    }

    @Override
    public List<Rating> findByOrderByIdDesc() {
        return ratingRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Rating> findTop4ByOrderByCreatedDateDescByOuvrierId(Long ouvRef) {
        return ratingRepository.findTop4RatingOrderByCreatedDateDesc(ouvRef);
    }

    @Override
    public List<Rating> FindListRatingByCustomerId(Long userId) {
        return ratingRepository.FindListRatingByCustomerId(userId);
    }

    @Override
    public BigDecimal countNumberOfNotificationByOuvrierId(Long idOuv) {
        return ratingRepository.countNumberOfRatingByOuvrierId(idOuv);
    }

    @Override
    public BigDecimal countNumberOfRating() {
        return ratingRepository.countNumberOfRating();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }
        ratingRepository.deleteById(id);
    }
}
