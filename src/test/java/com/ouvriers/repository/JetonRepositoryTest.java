package com.ouvriers.repository;


import com.ouvriers.models.Jeton;
import com.ouvriers.models.Utilisateur;
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
public class JetonRepositoryTest {

    @Autowired
    private JetonRepository jetonRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveJetonTest() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName("Tairou");
        utilisateur.setUsername("thir");
        utilisateur.setNomEntreprise("Wokite");
        utilisateurRepository.save(utilisateur);

        Jeton jeton = new Jeton();
        jeton.setNumero("25478");
        jeton.setMontant(245000);
        jeton.setEtat("Encours");
        jeton.setUtilisateur(utilisateur);
        jeton.setCreatedDate(new Date());
        jetonRepository.save(jeton);

        Assertions.assertThat(jeton.getId()).isGreaterThan(0);
        float montant = 245000;
        Assertions.assertThat(montant).isEqualTo(jeton.getMontant());
    }

    @Test
    @Order(2)
    public void getJetonByIdTest() {
        Optional<Jeton> optionalJeton = jetonRepository.findById(1L);
        if (optionalJeton.isPresent()) {
            assertThat(optionalJeton.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateJetonTest() {
        Optional<Jeton> optionalJeton = jetonRepository.findById(1L);
        if (optionalJeton.isPresent()) {
            Jeton jeton = optionalJeton.get();
            jeton.setEtat("Validee");

            Jeton jetonUpdated = jetonRepository.save(jeton);

            Assertions.assertThat(jetonUpdated.getEtat()).isEqualTo("Validee");
        }
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEtatJetonTest() {
        String etat = "Valide";
        Optional<Jeton> jetonOptional = jetonRepository.findById(1L);
        if (jetonOptional.isPresent()) {
            Jeton jeton = jetonOptional.get();
            jeton.setEtat(etat);
            Jeton jetonUpdated = jetonRepository.save(jeton);

            Assertions.assertThat(jetonUpdated.getEtat()).isEqualTo("Valide");
        }
    }

    @Test
    @Order(5)
    public void getListOfJetonsTest() {
        List<Jeton> jetonList = jetonRepository.findAll();
        assertThat(jetonList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(6)
    public void getAllJetonsByOrderByIdDescTest() {
        List<Jeton> jetonList = jetonRepository.findListOfJetonByOrderByIdDesc();
        assertThat(jetonList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(7)
    public void getListJetonByCustomerIdTest() {
        Long custmoerId = null;
        List<Jeton> jetonList = jetonRepository.FindListJetonByCustomerId(custmoerId);
        assertThat(jetonList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(8)
    public void getSumTotalOfJetonInYearTest() {
        BigDecimal number = jetonRepository.sumTotalOfJetonInYear();
        BigDecimal val = BigDecimal.valueOf(2000000.5);
        BigDecimal bdGood = BigDecimal.valueOf(2.5).setScale(3);
        BigDecimal bdBad = BigDecimal.valueOf(2.504);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(9)
    public void getNumbersOfJetonsTest() {
        long nbre = jetonRepository.count();
        assertThat(nbre).isNotNull();
    }

    @Test
    @Order(10)
    public void getNumberOfJetonsPeerMonthTest() {
        List<?> jetonList = jetonRepository.sumTotalOfJetonPeerMonth();
        assertThat(jetonList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(11)
    public void getNumberOfJetonsPeerYearTest() {
        List<?> jetonList = jetonRepository.sumTotalOfJetonPeerYear();
        assertThat(jetonList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(12)
    @Rollback(value = false)
    public void deleteJetonTest() {
        Optional<Jeton> jetonOptional = jetonRepository.findById(1L);
        if (jetonOptional.isPresent()) {
            jetonRepository.delete(jetonOptional.get());
        }
        Jeton jeton = null;
        Optional<Jeton> jetonOptional1 = jetonRepository.findById(1L);
        if (jetonOptional1.isPresent()) {
            jeton = jetonOptional1.get();
        }
        Assertions.assertThat(jeton).isNull();
    }

}
