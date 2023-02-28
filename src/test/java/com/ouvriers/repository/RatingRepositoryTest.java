package com.ouvriers.repository;

import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Rating;
import com.ouvriers.models.Utilisateur;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private OuvrierRepository ouvrierRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveRatingTest() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setUsername("thir");
        utilisateurRepository.save(utilisateur);
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setFirstName("Ablaye");
        ouvrier.setLastName("Diagne");
        ouvrier.setDisponibity("Plein temps");
        ouvrier.setEmail("ouvrier@gmail.com");
        ouvrierRepository.save(ouvrier);

        Rating rating = new Rating();
        rating.setId(1L);
        rating.setNbreEtoile(4);
        rating.setObservation("Rating01");
        rating.setOuvrier(ouvrier);
        rating.setUtilisateur(utilisateur);
        rating.setCreatedDate(new Date());
        ratingRepository.save(rating);

        assertThat(rating.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void saveRatingToOuvrierTest() {
        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findById(1L);
        Rating rating = new Rating();
        rating.setId(2L);
        rating.setNbreEtoile(5);
        rating.setObservation("Rating02");
        rating.setOuvrier(optionalOuvrier.get());
        rating.setCreatedDate(new Date());
        ratingRepository.save(rating);

        assertThat(rating.getId()).isGreaterThan(0);
    }


    @Test
    @Order(3)
    public void getRatingByIdTest() {
        Optional<Rating> optionalRating = ratingRepository.findById(1L);
        if (optionalRating.isPresent()) {
            assertThat(optionalRating.get()).isNotNull();
        }
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateRatingTest() {
        Optional<Rating> optionalRating = ratingRepository.findById(1L);
        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            rating.setNbreEtoile(5);

            Rating ratingUpdated = ratingRepository.save(rating);

            Assertions.assertThat(ratingUpdated.getNbreEtoile()).isEqualTo(5);
        }
    }

    @Test
    @Order(5)
    public void getAllRatingsTest() {
        List<Rating> ratingList = ratingRepository.findAll();
        assertThat(ratingList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(6)
    public void getAllRatingsOrderByIdDescTest() {
        List<Rating> ratingList = ratingRepository.findByOrderByIdDesc();
        assertThat(ratingList.size()).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(7)
    public void getTop3RatingsOrderByCreatedDateDescTest() {
        List<Rating> ratingList = ratingRepository.findTop3ByOrderByCreatedDateDesc();
        assertThat(ratingList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(8)
    public void getTop4RatingsByOuvrierIdOrderByCreatedDateDescTest() {
        Long ouvId = null;
        List<Rating> ratingList = ratingRepository.findTop4RatingOrderByCreatedDateDesc(ouvId);
        assertThat(ratingList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(9)
    public void getListRatingsByCustomerIdTest() {
        Long customerId = null;
        List<Rating> ratingList = ratingRepository.FindListRatingByCustomerId(customerId);
        assertThat(ratingList.size()).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(10)
    @Rollback(value = false)
    public void deleteRatingTest() {
        Optional<Rating> optionalRating = ratingRepository.findById(1L);
        if (optionalRating.isPresent()) {
            ratingRepository.delete(optionalRating.get());
        }
        Rating rating = null;
        Optional<Rating> optionalRating1 = ratingRepository.findById(1L);
        if (optionalRating1.isPresent()) {
            rating = optionalRating1.get();
        }
        Assertions.assertThat(rating).isNull();
    }
}
