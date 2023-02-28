package com.ouvriers.repository;


import com.ouvriers.models.Address;
import com.ouvriers.models.Locality;
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
public class LocalityRepositoryTest {

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveLocalityTest() {
        Address address = new Address();
        address.setCode("456");address.setName("Dakar");address.setCountry("Senegal");
        addressRepository.save(address);
        Locality locality = new Locality();
        locality.setRue("Rue 10"); locality.setName("Sacre coeur");
        locality.setCodePostal("78941");locality.setAddress(address);
        localityRepository.save(locality);

        Assertions.assertThat(locality.getId()).isNotNull();

        String name = "Sacre coeur";

        Assertions.assertThat(name).isEqualTo(locality.getName());
    }

    @Test
    @Order(2)
    public void getLocalityByIdTest() {
        Optional<Locality> optionalLocality = localityRepository.findById(1L);
        if (optionalLocality.isPresent()) {
            assertThat(optionalLocality.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateLocalityTest() {
        Optional<Locality> optionalLocality = localityRepository.findById(1L);
        if (optionalLocality.isPresent()) {
            Locality locality = optionalLocality.get();
            locality.setName("Hann-Mariste");

            Locality localityUpdated = localityRepository.save(locality);

            Assertions.assertThat(localityUpdated.getName()).isEqualTo("Hann-Mariste");
        }
    }

    @Test
    @Order(4)
    public void getListOfLocalitiesTest() {
        List<Locality> localityList = localityRepository.findAll();
        assertThat(localityList.size()).isNotNull();
    }

    @Test
    @Order(5)
    public void findAllLocalitiesByAddressCodeTest() {
        String code = "locA";
        List<Locality> localityList = localityRepository.findAllLocalityByAddressCode(code);
        assertThat(localityList.size()).isNotNull();
    }

    @Test
    @Order(6)
    public void getListOfLocalitiesOrderByIdDescTest() {
        List<Locality> localityList = localityRepository.findByOrderByIdDesc();
        assertThat(localityList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void deleteLocalityTest() {
        Optional<Locality> optionalLocality = localityRepository.findById(1L);
        if (optionalLocality.isPresent()) {
            localityRepository.delete(optionalLocality.get());
        }
        Locality locality = null;
        Optional<Locality> optionalLocality1 = localityRepository.findById(1L);
        if (optionalLocality1.isPresent()) {
            locality = optionalLocality1.get();
        }
        Assertions.assertThat(locality).isNull();
    }


}
