package com.ouvriers.services;

import com.ouvriers.models.Metier;
import com.ouvriers.repository.MetierRepository;
import com.ouvriers.services.Impl.MetierServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MetierServiceTest {

    @InjectMocks
    private MetierServiceImpl metierService;

    @Mock
    private MetierRepository metierRepository;

    @Test
    public void should_save_one_metier() {
        Metier metier = new Metier();
        metier.setReference("PLB");
        metier.setDesignation("Plombier");
        metier.setDescription("PLBPLBPLBPLB");
        when(metierRepository.save(any(Metier.class))).thenReturn(metier);

        Metier saveMetier = metierService.save(new Metier());

        assertThat(saveMetier).usingRecursiveComparison().isEqualTo(metier);
        verify(metierRepository, times(1)).save(any(Metier.class));
        verifyNoMoreInteractions(metierRepository);
    }

    @Test
    public void create_one_Metier() {
        Metier metier = new Metier();
        metier.setReference("ELEC");
        metier.setDesignation("Electricite");
        metier.setDescription("ELECELECELECELEC");
        when(metierRepository.save(metier)).thenReturn(metier);

        Metier metierSavedResult = metierService.save(metier);

        verify(metierRepository).save(metier);
        assertThat(metierRepository).isNotNull();
        assertThat(metierSavedResult).isEqualTo(metier);
        assertThat(metierSavedResult.getId()).isEqualTo(metier.getId());
        assertThat(metierSavedResult.getReference()).isEqualTo(metier.getReference());
        assertThat(metierSavedResult.getDesignation()).isEqualTo(metier.getDesignation());
    }

    @Test
    public void should_find_and_return_one_Metier() {
        Metier metier = Metier.builder()
                .reference("MEC")
                .designation("Mecanicien")
                .description("MMMMMMMMMMMMMM")
                .build();
        when(metierRepository.findById(anyLong())).thenReturn(Optional.of(metier));
        Metier metierResult = metierService.findById(anyLong());

        assertThat(metierResult).usingRecursiveComparison().isEqualTo(metier);
        verify(metierRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(metierRepository);
    }

    @Test
    public void findByIdTest() {
        Metier metier = Metier.builder()
                .id(1L)
                .reference("MET")
                .designation("Chauffeur")
                .build();

        when(metierRepository.findById(metier.getId())).thenReturn(Optional.of(metier));

        Metier metierSavedResult = metierService.findById(metier.getId());

        verify(metierRepository).findById(metier.getId());
        assertThat(metier).isNotNull();
        assertThat(metierSavedResult).isEqualTo(metier);
        assertThat(metierSavedResult.getId()).isEqualTo(metier.getId());

    }

    @Test
    public void should_update_metier() {
        Metier metier = Metier.builder()
                .id(2L)
                .reference("CP")
                .designation("Charpentier")
                .build();

        when(metierRepository.findById(anyLong())).thenReturn(Optional.of(metier));
        Metier metierResult = metierService.findById(metier.getId());
        metierResult.setReference("MP");
        Metier metierUpdated = metierService.save(metierResult);
        assertThat(metierUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_metier() {
        Metier metier = Metier.builder()
                .id(2L)
                .reference("CP")
                .designation("Charpentier")
                .build();
        when(metierRepository.findAll()).thenReturn(singletonList(metier));

        List<Metier> metierList = metierService.findAll();

        assertThat(metierList).isNotNull();
        assertThat(metierList).hasSize(1);
        verify(metierRepository, times(1)).findAll();
        verifyNoMoreInteractions(metierRepository);
        verify(metierRepository).findAll();
        assertThat(metierList.get(0)).isEqualTo(metier);
    }

    @Test
    public void should_find_and_return_all_metier_by_IdDesc() {
        Metier metier = Metier.builder()
                .id(2L)
                .reference("CP")
                .designation("Charpentier")
                .build();
        when(metierRepository.findListOfMetierByOrderByIdDesc()).thenReturn(singletonList(metier));

        List<Metier> metierList = metierService.findByMetierByIdDesc();

        assertThat(metierList).isNotNull();
        assertThat(metierList).hasSize(1);
        verify(metierRepository, times(1)).findListOfMetierByOrderByIdDesc();
        verifyNoMoreInteractions(metierRepository);
        verify(metierRepository).findListOfMetierByOrderByIdDesc();
        assertThat(metierList.get(0)).isEqualTo(metier);
    }

    /*
    @Test
    public void should_count_and_return_number_of_metier() {
        Metier metier = Metier.builder()
                .reference("CP")
                .designation("Charpentier")
                .build();
        when(metierRepository.count()).thenReturn(anyLong());

        long metier1 = metierService.countNumbersOfMetiers();

        assertThat(metier1).isGreaterThanOrEqualTo(1);
        verify(metierRepository, times(1)).count();
        verifyNoMoreInteractions(metierRepository);
    }*/

    @Test
    public void should_delete_one_metier() {
        doNothing().when(metierRepository).deleteById(anyLong());

        metierService.delete(anyLong());
        verify(metierRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(metierRepository);
    }


}
