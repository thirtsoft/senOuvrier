package com.ouvriers.services;

import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Rating;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.RatingRepository;
import com.ouvriers.repository.RoleRepository;
import com.ouvriers.services.Impl.RatingServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RatingServiceTest {

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Test
    public void should_save_one_rating() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName("Tairou");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setUsername("User01");
        utilisateur.setPassword("User01");
        Rating rating = new Rating();
        rating.setId(1L);
        rating.setNbreEtoile(5);
        rating.setObservation("Bien");
        rating.setOuvrier(ouvrier);
        rating.setUtilisateur(utilisateur);
        rating.setCreatedDate(new Date());

        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating ratingResult = ratingService.save(new Rating());

        assertThat(ratingResult).usingRecursiveComparison().isEqualTo(rating);
        verify(ratingRepository, times(1)).save(any(Rating.class));
        verifyNoMoreInteractions(ratingRepository);

    }

    @Test
    public void should_find_and_return_one_rating() {
        Rating rating = new Rating();
        rating.setId(1L);
        rating.setNbreEtoile(5);
        rating.setObservation("Bien");
        rating.setCreatedDate(new Date());

        when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        Rating ratingResult_find_Result = ratingService.findById(anyLong());

        assertThat(ratingResult_find_Result).usingRecursiveComparison().isEqualTo(rating);
        verify(ratingRepository, times(1)).findById(anyLong());

    }

    @Test
    public void should_update_rating() {
        Rating rating = new Rating();
        rating.setId(1L);
        rating.setNbreEtoile(5);
        rating.setObservation("Bien");
        rating.setCreatedDate(new Date());

        when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        Rating rating_find_Result = ratingService.findById(anyLong());
        rating_find_Result.setNbreEtoile(4);
        Rating ratingUpdated = ratingService.save(rating_find_Result);
        assertThat(ratingUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_ratings() {
        when(ratingRepository.findAll()).thenReturn(singletonList(new Rating()));

        assertThat(ratingService.findAll()).hasSize(1);
        verify(ratingRepository, times(1)).findAll();
        verifyNoMoreInteractions(ratingRepository);

    }

    @Test
    public void should_find_and_return_all_rating_by_IdDesc() {
        when(ratingRepository.findByOrderByIdDesc()).thenReturn(singletonList(new Rating()));

        assertThat(ratingService.findByOrderByIdDesc()).hasSize(1);
        verify(ratingRepository, times(1)).findByOrderByIdDesc();
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    public void should_find_and_return_top30_rating_by_IdDesc() {
        when(ratingRepository.findTop3ByOrderByCreatedDateDesc()).thenReturn(singletonList(new Rating()));

        assertThat(ratingService.findTop3RatingOrderByCreatedDateDesc()).hasSize(1);
        verify(ratingRepository, times(1)).findTop3ByOrderByCreatedDateDesc();
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    public void should_find_and_return_all_ratings_by_customerId() {
        Long customerId = 1L;
        when(ratingRepository.FindListRatingByCustomerId(customerId)).thenReturn(singletonList(new Rating()));

        assertThat(ratingService.FindListRatingByCustomerId(customerId)).hasSize(1);
        verify(ratingRepository, times(1)).FindListRatingByCustomerId(customerId);
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    public void should_find_and_return_top4_ratings_by_ouvrierId() {
        Long ouvId = 1L;
        when(ratingRepository.findTop4RatingOrderByCreatedDateDesc(ouvId)).thenReturn(singletonList(new Rating()));

        assertThat(ratingService.findTop4ByOrderByCreatedDateDescByOuvrierId(ouvId)).hasSize(1);
        verify(ratingRepository, times(1)).findTop4RatingOrderByCreatedDateDesc(ouvId);
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    public void should_delete_one_rating() {
        doNothing().when(ratingRepository).deleteById(anyLong());

        ratingService.delete(anyLong());
        verify(ratingRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(ratingRepository);
    }

}
