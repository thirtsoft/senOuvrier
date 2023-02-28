package com.ouvriers.repository;

import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.ServiceOffert;
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
public class ServiceOffertRepositoryTest {

    @Autowired
    private ServiceOffertRepository serviceOffertRepository;

    @Autowired
    private OuvrierRepository ouvrierRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveServiceOffertTest() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setFirstName("Ablaye");
        ouvrier.setLastName("Diagne");
        ouvrier.setDisponibity("Plein temps");
        ouvrier.setEmail("ouvrier@gmail.com");
        ouvrierRepository.save(ouvrier);
        ServiceOffert serviceOffert = new ServiceOffert();
        serviceOffert.setId(1L);
        serviceOffert.setReference("Serv01");
        serviceOffert.setDesignation("Depannage");
        serviceOffert.setDescription("Serv01");
        serviceOffert.setOuvrier(ouvrier);
        serviceOffertRepository.save(serviceOffert);

        assertThat(serviceOffert.getId()).isGreaterThan(0);
    }


    @Test
    @Order(2)
    public void getServiceOffertByIdTest() {
        Optional<ServiceOffert> optionalServiceOffert = serviceOffertRepository.findById(1L);
        if (optionalServiceOffert.isPresent()) {
            assertThat(optionalServiceOffert.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateServiceOffertTest() {
        Optional<ServiceOffert> optionalServiceOffert = serviceOffertRepository.findById(1L);
        if (optionalServiceOffert.isPresent()) {
            ServiceOffert serviceOffert = optionalServiceOffert.get();
            serviceOffert.setDesignation("Serv02");

            ServiceOffert serviceOffertUpdated = serviceOffertRepository.save(serviceOffert);

            Assertions.assertThat(serviceOffertUpdated.getDesignation()).isEqualTo("Serv02");
        }
    }

    @Test
    @Order(4)
    public void getAllServiceOffertsTest() {
        List<ServiceOffert> serviceOffertList = serviceOffertRepository.findAll();
        assertThat(serviceOffertList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(5)
    public void getAllServiceOffertsOrderByIdDescTest() {
        List<ServiceOffert> serviceOffertList = serviceOffertRepository.findByOrderByIdDesc();
        assertThat(serviceOffertList.size()).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(6)
    public void getAllServiceOffertsByOuvrierIdTest() {
        Long ouvId = null;
        List<ServiceOffert> serviceOffertList = serviceOffertRepository.findAllServiceOffertByOuvrierID(ouvId);
        assertThat(serviceOffertList.size()).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(7)
    @Rollback(value = false)
    public void deleteServiceOffertTest() {
        Optional<ServiceOffert> optionalServiceOffert = serviceOffertRepository.findById(1L);
        if (optionalServiceOffert.isPresent()) {
            serviceOffertRepository.delete(optionalServiceOffert.get());
        }
        ServiceOffert serviceOffert = null;
        Optional<ServiceOffert> optionalServiceOffert1 = serviceOffertRepository.findById(1L);
        if (optionalServiceOffert1.isPresent()) {
            serviceOffert = optionalServiceOffert1.get();
        }
        Assertions.assertThat(serviceOffert).isNull();
    }
}
