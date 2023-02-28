package com.ouvriers.repository;

import com.ouvriers.models.HistoriqueLogin;
import com.ouvriers.models.HistoriqueLogin;
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

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistoriqueLoginRepositoryTest {

    @Autowired
    private HistoriqueLoginRepository historiqueLoginRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveHisotiriqueLogin() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName("Tairou");utilisateur.setNomEntreprise("Wokite");
        utilisateurRepository.save(utilisateur);
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setAction("Se connecter");
        historiqueLogin.setUtilisateur(utilisateur);
        historiqueLogin.setCreatedDate(new Date());
        historiqueLoginRepository.save(historiqueLogin);
        assertThat(historiqueLogin).isNotNull();
        assertThat(historiqueLogin.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getHistoriqueLoginByIdTest() {
        Optional<HistoriqueLogin> optionalHistoriqueLogin = historiqueLoginRepository.findById(1L);
        if (optionalHistoriqueLogin.isPresent()) {
            assertThat(optionalHistoriqueLogin.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateHistoriqueLoginTest() {
        Optional<HistoriqueLogin> optionalHistoriqueLogin = historiqueLoginRepository.findById(1L);
        if (optionalHistoriqueLogin.isPresent()) {
            HistoriqueLogin historiqueLogin = optionalHistoriqueLogin.get();
            historiqueLogin.setAction("Se Deconnecter");

            HistoriqueLogin historiqueLoginUpdated = historiqueLoginRepository.save(historiqueLogin);

            Assertions.assertThat(historiqueLoginUpdated.getAction()).isEqualTo("Se Deconnecter");
        }
    }

    @Test
    @Order(4)
    public void getAllHistoriqueLoginsTest() {
        List<HistoriqueLogin> HistoriqueLoginList = historiqueLoginRepository.findAll();
        assertThat(HistoriqueLoginList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(5)
    public void getAllHistoriqueLoginsOrderByIdDescTest() {
        List<HistoriqueLogin> HistoriqueLoginList = historiqueLoginRepository.findHistoriqueLoginByOrderByIdDesc();
        assertThat(HistoriqueLoginList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteHistoriqueLoginTest() {
        Optional<HistoriqueLogin> optionalHistoriqueLogin = historiqueLoginRepository.findById(1L);
        if (optionalHistoriqueLogin.isPresent()) {
            historiqueLoginRepository.delete(optionalHistoriqueLogin.get());
        }
        HistoriqueLogin historiqueLogin = null;
        Optional<HistoriqueLogin> optionalHistoriqueLogin1 = historiqueLoginRepository.findById(1L);
        if (optionalHistoriqueLogin1.isPresent()) {
            historiqueLogin = optionalHistoriqueLogin1.get();
        }
        Assertions.assertThat(historiqueLogin).isNull();
    }

}
