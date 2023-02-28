package com.ouvriers.repository;

import com.ouvriers.models.Metier;
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
public class MetierRepositoryTest {

    @Autowired
    private MetierRepository metierRepository;

    // Junit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void testCreateMetier() {
        Metier metier = new Metier();
        metier.setReference("Metier05");
        metier.setDesignation("Plombier5");
        metier.setDescription("Plombier5");
        metierRepository.save(metier);

        assertThat(metier.getId()).isGreaterThan(0);

        String designation = "Plombier5";

        assertThat(designation)
                .isEqualTo(metier.getDesignation());
    }

    @Test
    @Order(2)
    public void getMetierByIdTest() {
        Metier metier = metierRepository.save(new Metier(2L, "Met02", "Met02", "Met02", "Met02"));

        Metier optionalMetier = metierRepository.findById(metier.getId()).get();

        assertThat(optionalMetier.getId()).isEqualTo(metier.getId());

    }

    @Test
    @Order(3)
    public void getListOfMetiersTest() {

        List<Metier> metierList = metierRepository.findAll();
        assertThat(metierList.size()).isNotNull();
    }

    @Test
    @Order(4)
    public void getListOfMetiersByIdDescTest() {
        List<Metier> metierList = metierRepository.findListOfMetierByOrderByIdDesc();
        assertThat(metierList.size()).isNotNull();
    }

    @Test
    @Order(5)
    public void countNumberOfMetierTest() {
        long nbre = metierRepository.count();
        assertThat(nbre).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(6)
    @Rollback(value = false)
    public void updateMetierTest() {

        Optional<Metier> optionalMetier = metierRepository.findById(1L);
        if (optionalMetier.isPresent()) {
            Metier metier = optionalMetier.get();
            metier.setReference("Metier04");

            Metier metierUpdated = metierRepository.save(metier);

            Assertions.assertThat(metierUpdated.getReference()).isEqualTo("Metier04");
        }

    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void deleteMetierTest() {
        Optional<Metier> metier = metierRepository.findById(1L);
        if (metier.isPresent()) {
            metierRepository.delete(metier.get());
        }
        Metier metier1 = null;
        Optional<Metier> optionalMetier = metierRepository.findById(1L);
        if (optionalMetier.isPresent()) {
            metier1 = optionalMetier.get();
        }
        Assertions.assertThat(metier1).isNull();
    }


}
