package com.ouvriers.repository;


import com.ouvriers.models.Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAddressTest() {
        Address address = new Address();
        address.setCode("233");
        address.setName("Address01");
        address.setCountry("SEN");
        addressRepository.save(address);

        assertThat(address.getId()).isGreaterThan(0);

        String country = "SEN";

        assertThat(country)
                .isEqualTo(address.getCountry());
    }

    @Test
    @Order(2)
    public void getAddressByIdTest() {
        Address address = new Address();
        address.setCode("233");
        address.setName("Address01");
        address.setCountry("SEN");
        addressRepository.save(address);

        Address optionalAddress = addressRepository.findById(address.getId()).get();

        assertThat(optionalAddress.getId()).isEqualTo(address.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateAddressTest() {
        Optional<Address> optionalAddress = addressRepository.findById(1L);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setName("Dakar");

            Address addressUpdated = addressRepository.save(address);

            Assertions.assertThat(addressUpdated.getName()).isEqualTo("Dakar");
        }
    }

    @Test
    @Order(4)
    public void getListOfAdressesTest() {
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList.size()).isNotNull();
    }

    @Test
    @Order(5)
    public void getListOfAdressesByIdDescTest() {
        List<Address> addressList = addressRepository.findAddresseByOrderByIdDesc();
        assertThat(addressList.size()).isNotNull();
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteAddressTest() {
        Optional<Address> optionalAddress = addressRepository.findById(1L);
        if (optionalAddress.isPresent()) {
            addressRepository.delete(optionalAddress.get());
        }
        Address address = null;
        Optional<Address> optionalAddress1 = addressRepository.findById(1L);
        if (optionalAddress1.isPresent()) {
            address = optionalAddress1.get();
        }
        Assertions.assertThat(address).isNull();
    }


}
