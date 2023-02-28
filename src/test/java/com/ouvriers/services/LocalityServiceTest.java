package com.ouvriers.services;

import com.ouvriers.models.Address;
import com.ouvriers.models.Locality;
import com.ouvriers.repository.LocalityRepository;
import com.ouvriers.services.Impl.LocalityServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LocalityServiceTest {

    @InjectMocks
    private LocalityServiceImpl localityService;

    @Mock
    private LocalityRepository localityRepository;

    @Test
    public void should_save_one_locality() {
        Address address = new Address();
        address.setName("ADD01");
        address.setName("SEN");
        address.setCode("LOC01");
        Locality locality = new Locality();
        locality.setCodePostal("457816");
        locality.setName("Mariste 2");
        locality.setAddress(address);

        when(localityRepository.save(any(Locality.class))).thenReturn(locality);

        Locality localityResult = localityService.save(new Locality());

        assertThat(localityResult).usingRecursiveComparison().isEqualTo(locality);
        verify(localityRepository, times(1)).save(any(Locality.class));
        verifyNoMoreInteractions(localityRepository);

    }

    @Test
    public void should_find_and_return_one_locality() {
        Locality locality = new Locality();
        locality.setCodePostal("457816");
        locality.setName("Mariste 2");

        when(localityRepository.findById(anyLong())).thenReturn(Optional.of(locality));

        Locality locality_find_Result = localityService.findById(anyLong());

        assertThat(locality_find_Result).usingRecursiveComparison().isEqualTo(locality);
        verify(localityRepository, times(1)).findById(anyLong());

    }

    @Test
    public void should_update_locality() {
        Locality locality = new Locality();
        locality.setCodePostal("457816");
        locality.setName("Mariste 2");

        when(localityRepository.findById(anyLong())).thenReturn(Optional.of(locality));

        Locality locality_find_Result = localityService.findById(anyLong());
        locality_find_Result.setCodePostal("7845961");
        Locality localityUpdated = localityService.save(locality_find_Result);
        assertThat(localityUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_localities() {
        when(localityRepository.findAll()).thenReturn(singletonList(new Locality()));

        assertThat(localityService.findAll()).hasSize(1);
        verify(localityRepository, times(1)).findAll();
        verifyNoMoreInteractions(localityRepository);

    }

    @Test
    public void should_find_and_return_all_localities_by_IdDesc() {
        when(localityRepository.findByOrderByIdDesc()).thenReturn(singletonList(new Locality()));

        assertThat(localityService.findByLocalityByIdDesc()).hasSize(1);
        verify(localityRepository, times(1)).findByOrderByIdDesc();
        verifyNoMoreInteractions(localityRepository);
    }

    @Test
    public void should_find_and_return_localities_by_address_code() {
        String addCode = "Add01";

        when(localityRepository.findAllLocalityByAddressCode(addCode)).thenReturn(singletonList(new Locality()));

        assertThat(localityService.findAllLocalitiesByAddressCode(addCode)).hasSize(1);
        verify(localityRepository, times(1)).findAllLocalityByAddressCode(addCode);
        verifyNoMoreInteractions(localityRepository);
    }

    @Test
    public void should_delete_one_locality() {
        doNothing().when(localityRepository).deleteById(anyLong());

        localityService.delete(anyLong());
        verify(localityRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(localityRepository);
    }

}
