package com.ouvriers.repository;

import com.ouvriers.models.Locality;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Prestation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrestationRepositoryTest {

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private OuvrierRepository ouvrierRepository;
    @Autowired
    private LocalityRepository localityRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void savePrestationTest() {
        Locality locality = new Locality();
        locality.setId(1L);
        locality.setName("Hann-Mariste");
        localityRepository.save(locality);
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setFirstName("Ablaye");
        ouvrier.setLastName("Diagne");
        ouvrier.setDisponibity("Plein temps");
        ouvrier.setEmail("ouvrier@gmail.com");
        ouvrierRepository.save(ouvrier);

        Prestation prestation = new Prestation();
        prestation.setId(1L);
        prestation.setTitle("Plafonnage");
        prestation.setDescription("Plafonnageappointment");
        prestation.setOuvrier(ouvrier);
        prestation.setLocality(locality);
        prestation.setCreatedDate(new Date());
        prestationRepository.save(prestation);

        assertThat(prestation.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getPrestationByIdTest() {
        Optional<Prestation> optionalPrestation = prestationRepository.findById(1L);
        if (optionalPrestation.isPresent()) {
            assertThat(optionalPrestation.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updatePrestationTest() {
        Optional<Prestation> optionalPrestation = prestationRepository.findById(1L);
        if (optionalPrestation.isPresent()) {
            Prestation prestation = optionalPrestation.get();
            prestation.setTitle("Carrolage");

            Prestation prestationUpdated = prestationRepository.save(prestation);

            Assertions.assertThat(prestationUpdated.getTitle()).isEqualTo("Carrolage");
        }
    }

    @Test
    @Order(4)
    public void getNumbersOfPrestationsTest() {
        Long ouvId = null;
        BigDecimal nbre = prestationRepository.countNumberOfPrestationByOuvrierId(ouvId);
        assertThat(nbre).isNotNull();
    }

    @Test
    @Order(5)
    public void getAllPrestationsTest() {
        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(6)
    public void getAllPrestationsOrderByIdDescTest() {
        List<Prestation> prestationList = prestationRepository.findPrestationByOrderByIdDesc();
        assertThat(prestationList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(7)
    public void getAllPrestationsByOuvrierIdTest() {
        Long ouvId = null;
        List<Prestation> prestationList = prestationRepository.FindPrestationsByOuvrierId(ouvId);
        assertThat(prestationList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(8)
    public void getTop4PrestationsOrderByCreatedDateDescTest() {
        Long ouvId = null;
        List<Prestation> prestationList = prestationRepository.findTop4PrestationOrderByCreatedDateDesc(ouvId);
        assertThat(prestationList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(9)
    public void getTop8PrestationsOrderByCreatedDateDescTest() {
        Long ouvId = null;
        List<Prestation> prestationList = prestationRepository.findTop8PrestationOrderByCreatedDateDesc(ouvId);
        assertThat(prestationList.size()).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(10)
    @Rollback(value = false)
    public void deletePrestationTest() {
        Optional<Prestation> optionalPrestation = prestationRepository.findById(1L);
        if (optionalPrestation.isPresent()) {
            prestationRepository.delete(optionalPrestation.get());
        }
        Prestation Prestation = null;
        Optional<Prestation> optionalPrestation1 = prestationRepository.findById(1L);
        if (optionalPrestation1.isPresent()) {
            Prestation = optionalPrestation1.get();
        }
        Assertions.assertThat(Prestation).isNull();
    }


}
