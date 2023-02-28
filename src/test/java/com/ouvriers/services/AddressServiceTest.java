package com.ouvriers.services;

import com.ouvriers.models.Address;
import com.ouvriers.repository.AddressRepository;
import com.ouvriers.services.Impl.AddressServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;


    @Test
    public void should_save_one_address() {
        Address address = new Address();
        address.setCode("SN");
        address.setName("Dakar");
        address.setCountry("SENEGAL");
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address saveAddress = addressService.save(new Address());

        assertThat(saveAddress).usingRecursiveComparison().isEqualTo(address);
        verify(addressRepository, times(1)).save(any(Address.class));
        verifyNoMoreInteractions(addressRepository);
    }

    @Test
    public void should_find_and_return_one_Address() {
        Address address = new Address();
        address.setCode("SN");
        address.setName("Dakar");
        address.setCountry("SENEGAL");

        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        Address addressResult = addressService.findById(anyLong());

        assertThat(addressResult).usingRecursiveComparison().isEqualTo(address);
        verify(addressRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(addressRepository);
    }

    @Test
    public void should_update_address() {
        Address address = new Address();
        address.setId(1L);
        address.setCode("GB");
        address.setName("Banjul");
        address.setCountry("GAMBIA");

        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        Address addressResult = addressService.findById(address.getId());
        addressResult.setName("MP");
        Address addressUpdated = addressService.save(addressResult);
        assertThat(addressUpdated).isNull();

    }


    @Test
    public void should_find_and_return_all_address() {
        Address address = new Address();
        address.setCode("SN");
        address.setName("Dakar");
        address.setCountry("SENEGAL");
        when(addressRepository.findAll()).thenReturn(singletonList(address));

        List<Address> addressList = addressService.findAll();

        assertThat(addressList).isNotNull();
        assertThat(addressList).hasSize(1);
        verify(addressRepository, times(1)).findAll();
        verifyNoMoreInteractions(addressRepository);
    }

    @Test
    public void should_find_and_return_all_address_by_IdDesc() {
        Address address = new Address();
        address.setId(1L);
        address.setCode("GN");
        address.setName("Conakry");
        address.setCountry("Guinee");
        when(addressRepository.findAddresseByOrderByIdDesc()).thenReturn(singletonList(address));

        List<Address> addressList = addressService.findByAddresseByIdDesc();

        assertThat(addressList).hasSize(1);
        verify(addressRepository, times(1)).findAddresseByOrderByIdDesc();
        verifyNoMoreInteractions(addressRepository);
    }

    @Test
    public void should_delete_one_address() {
        doNothing().when(addressRepository).deleteById(anyLong());

        addressService.delete(anyLong());
        verify(addressRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(addressRepository);
    }

}
