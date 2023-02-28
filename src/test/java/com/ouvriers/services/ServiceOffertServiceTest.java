package com.ouvriers.services;

import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.ServiceOffert;
import com.ouvriers.repository.ServiceOffertRepository;
import com.ouvriers.services.Impl.ServiceOffertServiceImpl;
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
public class ServiceOffertServiceTest {

    @InjectMocks
    private ServiceOffertServiceImpl serviceOffertService;

    @Mock
    private ServiceOffertRepository serviceOffertRepository;

    @Test
    public void should_save_one_serviceoffert() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ServiceOffert serviceOffert = new ServiceOffert();
        serviceOffert.setReference("SERV01");
        serviceOffert.setDesignation("SERVICE01");
        serviceOffert.setOuvrier(ouvrier);

        when(serviceOffertRepository.save(any(ServiceOffert.class))).thenReturn(serviceOffert);

        ServiceOffert serviceOffertResult = serviceOffertService.save(new ServiceOffert());

        assertThat(serviceOffertResult).usingRecursiveComparison().isEqualTo(serviceOffert);
        verify(serviceOffertRepository, times(1)).save(any(ServiceOffert.class));
        verifyNoMoreInteractions(serviceOffertRepository);

    }

    @Test
    public void should_find_and_return_one_serviceoffert() {
        ServiceOffert serviceOffert = new ServiceOffert();
        serviceOffert.setReference("SERV01");
        serviceOffert.setDesignation("SERVICE01");

        when(serviceOffertRepository.findById(anyLong())).thenReturn(Optional.of(serviceOffert));

        ServiceOffert serviceOffert_find_Result = serviceOffertService.findById(anyLong());

        assertThat(serviceOffert_find_Result).usingRecursiveComparison().isEqualTo(serviceOffert);
        verify(serviceOffertRepository, times(1)).findById(anyLong());

    }

    @Test
    public void should_update_serviceoffert() {
        ServiceOffert serviceOffert = new ServiceOffert();
        serviceOffert.setReference("SERV01");
        serviceOffert.setDesignation("SERVICE01");

        when(serviceOffertRepository.findById(anyLong())).thenReturn(Optional.of(serviceOffert));

        ServiceOffert serviceOffert_find_Result = serviceOffertService.findById(anyLong());
        serviceOffert_find_Result.setReference("PREST004");
        ServiceOffert serviceOffertUpdated = serviceOffertService.save(serviceOffert_find_Result);
        assertThat(serviceOffertUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_serviceofferts() {
        when(serviceOffertRepository.findAll()).thenReturn(singletonList(new ServiceOffert()));

        assertThat(serviceOffertService.findAll()).hasSize(1);
        verify(serviceOffertRepository, times(1)).findAll();
        verifyNoMoreInteractions(serviceOffertRepository);

    }

    @Test
    public void should_find_and_return_all_serviceofferts_by_IdDesc() {
        when(serviceOffertRepository.findByOrderByIdDesc()).thenReturn(singletonList(new ServiceOffert()));

        assertThat(serviceOffertService.findByServiceOffertByIdDesc()).hasSize(1);
        verify(serviceOffertRepository, times(1)).findByOrderByIdDesc();
        verifyNoMoreInteractions(serviceOffertRepository);
    }

    @Test
    public void should_find_and_return_all_serviceofferts_by_ouvrierId() {
        Long ouvId = 1L;
        when(serviceOffertRepository.findAllServiceOffertByOuvrierID(ouvId)).thenReturn(singletonList(new ServiceOffert()));

        assertThat(serviceOffertService.findAllServiceOffertByOuvrierId(ouvId)).hasSize(1);
        verify(serviceOffertRepository, times(1)).findAllServiceOffertByOuvrierID(ouvId);
        verifyNoMoreInteractions(serviceOffertRepository);
    }

    @Test
    public void should_delete_one_serviceoffert() {
        doNothing().when(serviceOffertRepository).deleteById(anyLong());

        serviceOffertService.delete(anyLong());
        verify(serviceOffertRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(serviceOffertRepository);
    }


}
