package com.ouvriers.services;

import com.ouvriers.models.Locality;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Prestation;
import com.ouvriers.repository.PrestationRepository;
import com.ouvriers.services.Impl.PrestationServiceImpl;
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
public class PrestationServiceTest {

    @InjectMocks
    private PrestationServiceImpl prestationService;

    @Mock
    private PrestationRepository prestationRepository;

    @Test
    public void should_save_one_prestation() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        Locality locality = new Locality();
        locality.setCodePostal("47859");
        locality.setName("Loc01");
        Prestation prestation = new Prestation();
        prestation.setId(1L);
        prestation.setTitle("PREST01");
        prestation.setOuvrier(ouvrier);
        prestation.setLocality(locality);
        prestation.setCreatedDate(new Date());

        when(prestationRepository.save(any(Prestation.class))).thenReturn(prestation);

        Prestation prestationResult = prestationService.save(new Prestation());

        assertThat(prestationResult).usingRecursiveComparison().isEqualTo(prestation);
        verify(prestationRepository, times(1)).save(any(Prestation.class));
        verifyNoMoreInteractions(prestationRepository);

    }

    @Test
    public void should_find_and_return_one_prestation() {
        Prestation prestation = new Prestation();
        prestation.setId(1L);
        prestation.setTitle("PREST01");
        prestation.setCreatedDate(new Date());

        when(prestationRepository.findById(anyLong())).thenReturn(Optional.of(prestation));

        Prestation prestation_find_Result = prestationService.findById(anyLong());

        assertThat(prestation_find_Result).usingRecursiveComparison().isEqualTo(prestation);
        verify(prestationRepository, times(1)).findById(anyLong());

    }

    @Test
    public void should_update_prestation() {
        Prestation prestation = new Prestation();
        prestation.setId(1L);
        prestation.setTitle("PREST01");
        prestation.setCreatedDate(new Date());

        when(prestationRepository.findById(anyLong())).thenReturn(Optional.of(prestation));

        Prestation prestation_find_Result = prestationService.findById(anyLong());
        prestation_find_Result.setTitle("PREST004");
        Prestation prestationUpdated = prestationService.save(prestation_find_Result);
        assertThat(prestationUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_prestations() {
        when(prestationRepository.findAll()).thenReturn(singletonList(new Prestation()));

        assertThat(prestationService.findAll()).hasSize(1);
        verify(prestationRepository, times(1)).findAll();
        verifyNoMoreInteractions(prestationRepository);

    }

    @Test
    public void should_find_and_return_all_prestations_by_IdDesc() {
        when(prestationRepository.findPrestationByOrderByIdDesc()).thenReturn(singletonList(new Prestation()));

        assertThat(prestationService.findAllPrestationsOrderByIdDesc()).hasSize(1);
        verify(prestationRepository, times(1)).findPrestationByOrderByIdDesc();
        verifyNoMoreInteractions(prestationRepository);
    }

    @Test
    public void should_find_and_return_all_prestations_ouvrierId() {
        Long ouvId = 1L;
        when(prestationRepository.FindPrestationsByOuvrierId(ouvId)).thenReturn(singletonList(new Prestation()));

        assertThat(prestationService.FindAllPrestationsOrderByOuvrierId(ouvId)).hasSize(1);
        verify(prestationRepository, times(1)).FindPrestationsByOuvrierId(ouvId);
        verifyNoMoreInteractions(prestationRepository);
    }

    @Test
    public void should_find_and_return_top4_prestations_ouvrierId() {
        Long ouvId = 1L;
        when(prestationRepository.findTop4PrestationOrderByCreatedDateDesc(ouvId)).thenReturn(singletonList(new Prestation()));

        assertThat(prestationService.findTop4PrestationsOrderByCreatedDateDesc(ouvId)).hasSize(1);
        verify(prestationRepository, times(1)).findTop4PrestationOrderByCreatedDateDesc(ouvId);
        verifyNoMoreInteractions(prestationRepository);
    }

    @Test
    public void should_delete_one_prestation() {
        doNothing().when(prestationRepository).deleteById(anyLong());

        prestationService.delete(anyLong());
        verify(prestationRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(prestationRepository);
    }

}
