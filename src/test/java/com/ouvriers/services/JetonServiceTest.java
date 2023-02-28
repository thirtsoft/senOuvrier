package com.ouvriers.services;

import com.ouvriers.models.Jeton;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.JetonRepository;
import com.ouvriers.services.Impl.JetonServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JetonServiceTest {

    @InjectMocks
    private JetonServiceImpl jetonService;

    @Mock
    private JetonRepository jetonRepository;

    @Test
    public void should_save_one_jeton() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setName("Tairou");
        user.setNomEntreprise("Wokite");
        user.setUsername("username");
        user.setPassword("passer123");

        Jeton jeton = new Jeton();
        jeton.setId(1L);
        jeton.setNumero("233546");
        jeton.setMontant(456201);
        jeton.setEtat("waiting");
        jeton.setUtilisateur(user);
        jeton.setCreatedDate(new Date());

        when(jetonRepository.save(any(Jeton.class))).thenReturn(jeton);

        Jeton jetonSavedResult = jetonService.save(new Jeton());

        assertThat(jetonSavedResult).usingRecursiveComparison().isEqualTo(jeton);
        verify(jetonRepository, times(1)).save(any(Jeton.class));
        verifyNoMoreInteractions(jetonRepository);

    }

    @Test
    public void should_find_and_return_one_jeton() {
        Jeton jeton = new Jeton();
        jeton.setId(1L);
        jeton.setNumero("233546");
        jeton.setMontant(456201);
        jeton.setEtat("waiting");
        jeton.setCreatedDate(new Date());

        when(jetonRepository.findById(anyLong())).thenReturn(Optional.of(jeton));

        Jeton jeton_find_Result = jetonService.findById(anyLong());

        assertThat(jeton_find_Result).usingRecursiveComparison().isEqualTo(jeton);
        verify(jetonRepository, times(1)).findById(anyLong());

    }

    @Test
    public void should_update_jeton() {
        Jeton jeton = new Jeton();
        jeton.setId(1L);
        jeton.setNumero("233546");
        jeton.setMontant(456201);
        jeton.setEtat("waiting");
        jeton.setCreatedDate(new Date());

        when(jetonRepository.findById(anyLong())).thenReturn(Optional.of(jeton));

        Jeton jeton_find_Result = jetonService.findById(anyLong());
        jeton_find_Result.setNumero("7845961");
        Jeton jetonUpdated = jetonService.save(jeton_find_Result);
        assertThat(jetonUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_jetons() {
        when(jetonRepository.findAll()).thenReturn(singletonList(new Jeton()));

        assertThat(jetonService.findAll()).hasSize(1);
        verify(jetonRepository, times(1)).findAll();
        verifyNoMoreInteractions(jetonRepository);

    }

    @Test
    public void should_find_and_return_all_jetons_by_IdDesc() {
        when(jetonRepository.findAll()).thenReturn(singletonList(new Jeton()));

        assertThat(jetonService.findAll()).hasSize(1);
        verify(jetonRepository, times(1)).findAll();
        verifyNoMoreInteractions(jetonRepository);
    }

    @Test
    public void should_find_and_return_all_jetons_by_customerId() {
        Long customerId = 1L;
        when(jetonRepository.FindListJetonByCustomerId(customerId)).thenReturn(singletonList(new Jeton()));

        assertThat(jetonService.FindListJetonByCustomerId(customerId)).hasSize(1);
        verify(jetonRepository, times(1)).FindListJetonByCustomerId(customerId);
        verifyNoMoreInteractions(jetonRepository);
    }

    @Test
    public void should_delete_one_jeton() {
        doNothing().when(jetonRepository).deleteById(anyLong());

        jetonService.delete(anyLong());
        verify(jetonRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(jetonRepository);
    }


}
