package com.ouvriers.controllers;

import com.ouvriers.controllers.api.RatingApi;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Rating;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.services.OuvrierService;
import com.ouvriers.services.RatingService;
import com.ouvriers.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RatingController implements RatingApi {

    private final RatingService ratingService;

    private final OuvrierService ouvrierService;

    private final UtilisateurService utilisateurService;

    @Autowired
    public RatingController(RatingService ratingService, OuvrierService ouvrierService, UtilisateurService utilisateurService) {
        this.ratingService = ratingService;
        this.ouvrierService = ouvrierService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<Rating> save(Rating rating) {
        rating.setCreatedDate(new Date());
        Rating newNotificationDto = ratingService.save(rating);
        return new ResponseEntity<>(newNotificationDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Rating> saveNoteToOuvrier(Long id, Rating rating) {
        rating.setCreatedDate(new Date());
        Rating newNotificationDto = ratingService.saveNoteToOuvrier(id, rating);
        return new ResponseEntity<>(newNotificationDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Rating> saveRating(Rating rating, Long idOuv, Long id) {

        Ouvrier ouvrier = Optional.of(ouvrierService.findById(idOuv)).get();

        Utilisateur utilisateur = Optional.of(utilisateurService.findById(id)).get();

        rating.setOuvrier(ouvrier);
        rating.setUtilisateur(utilisateur);

        rating.setCreatedDate(new Date());

        Rating notificationDtoResult = ratingService.save(rating);

        return new ResponseEntity<>(notificationDtoResult, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Rating> update(Long id, Rating rating) {
        rating.setId(id);
        Rating newNotificationDto = ratingService.save(rating);
        return new ResponseEntity<>(newNotificationDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Rating> getRatingById(Long id) {
        Rating newNotificationDto = ratingService.findById(id);
        return new ResponseEntity<>(newNotificationDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratingList = ratingService.findAll();
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getAllRatingsOrderByIdDesc() {
        List<Rating> ratingList = ratingService.findByOrderByIdDesc();
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getTop3ByOrderByCreatedDateDesc() {
        List<Rating> ratingList = ratingService.findTop3RatingOrderByCreatedDateDesc();
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getTop30ByOrderByCreatedDateDesc() {
        List<Rating> ratingList = ratingService.findTop30RatingsOrderByCreatedDateDesc();
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getTop4ByOrderByCreatedDateDescByOuvrierId(Long id) {
        List<Rating> newNotificationDtos = ratingService.findTop4ByOrderByCreatedDateDescByOuvrierId(id);
        return new ResponseEntity<>(newNotificationDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getListRatingByCustomerId(Long id) {
        List<Rating> newNotificationDtos = ratingService.FindListRatingByCustomerId(id);
        return new ResponseEntity<>(newNotificationDtos, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfNotificationByOuvrierId(Long id) {
        return ratingService.countNumberOfNotificationByOuvrierId(id);
    }

    @Override
    public BigDecimal countNumberOfNotification() {
        return ratingService.countNumberOfRating();
    }

    @Override
    public void delete(Long id) {
        ratingService.delete(id);
    }
}
