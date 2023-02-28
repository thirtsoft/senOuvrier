package com.ouvriers.repository;

import com.ouvriers.enums.RoleName;
import com.ouvriers.models.Role;
import com.ouvriers.models.Utilisateur;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUtilisateurTest() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName("Tairou");
        utilisateur.setNomEntreprise("Wokite");
        utilisateurRepository.save(utilisateur);

        assertThat(utilisateur.getId()).isGreaterThan(0);
    }


    @Test
    @Order(2)
    public void getUtilisateurByIdTest() {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(1L);
        if (optionalUtilisateur.isPresent()) {
            assertThat(optionalUtilisateur.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    public void getUtilisateurByUsernameTest() {
        String username = "";
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByUsername(username);
        if (optionalUtilisateur.isPresent()) {
            assertThat(optionalUtilisateur.get()).isNotNull();
        }
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUtilisateurTest() {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(1L);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            utilisateur.setName("Saliou");

            Utilisateur utilisateurUpdated = utilisateurRepository.save(utilisateur);

            Assertions.assertThat(utilisateurUpdated.getName()).isEqualTo("Saliou");
        }
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void updateUtilisateurByUsernameTest() {
        String username = "";
        String newUsername = "Tata";
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByUsername(username);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            utilisateur.setUsername(newUsername);

            Utilisateur utilisateurUpdated = utilisateurRepository.save(utilisateur);

            Assertions.assertThat(utilisateurUpdated.getUsername()).isEqualTo("Tata");
        }
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void updatePasswordOfUtilisateurTest() {
        String olderPassword = "Password";
        String newPassword = "newPassword";
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(1L);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            if (passwordEncoder.matches(olderPassword, utilisateur.getPassword())) {
                utilisateur.setPassword(passwordEncoder.encode(newPassword));
                Utilisateur utilisateurUpdated = utilisateurRepository.save(utilisateur);

                Assertions.assertThat(utilisateurUpdated.getPassword()).isNotNull();
            }
        }
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void activatedUtilisateurTest() {
        boolean isActive = true;
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(1L);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            utilisateur.setActive(isActive);

            Utilisateur utilisateurUpdated = utilisateurRepository.save(utilisateur);

            Assertions.assertThat(utilisateurUpdated.isActive()).isTrue();
        }
    }


    @Test
    @Order(8)
    @Rollback(value = false)
    public void updateUtilisateurByIdTest() {
        String newUsername = "Mama";
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(1L);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            utilisateur.setUsername(newUsername);

            Utilisateur utilisateurUpdated = utilisateurRepository.save(utilisateur);

            Assertions.assertThat(utilisateurUpdated.getUsername()).isEqualTo("Mama");
        }
    }

    @Test
    @Order(9)
    public void addRoleToUtilisateurTest() {
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(adminRole);
        String username = "";
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        if (optionalRole.isPresent()) {
            Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByUsername(username);
            if (optionalUtilisateur.isPresent()) {
                Utilisateur utilisateur = optionalUtilisateur.get();
                utilisateur.getRoles().add(optionalRole.get());
                Assertions.assertThat(utilisateur.getRoles()).isNotNull();

            }

        }
    }

    @Test
    @Order(10)
    public void getNumberOfRegisterTest() {
        long number = utilisateurRepository.count();
        assertThat(number).isNotNull();
    }

    @Test
    @Order(11)
    public void getNumberOfRegisterInMonthTest() {
        BigDecimal number = utilisateurRepository.countNumberOfRegisterInMonth();
        BigDecimal val = BigDecimal.valueOf(2000000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(12)
    public void getAllUtilisateursTest() {
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(13)
    public void getAllUtilisateursOrderByIdDescTest() {
        List<Utilisateur> utilisateurList = utilisateurRepository.findByOrderByIdDesc();
        assertThat(utilisateurList.size()).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(14)
    public void getNewsRegisterInMonthByOrderByIdDescTest() {
        List<Utilisateur> utilisateurList = utilisateurRepository.findUtilisateursByOrderByIdDesc();
        assertThat(utilisateurList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(15)
    public void getNumberTotalOfRegisterPeerMonthTest() {
        List<?> utilisateurList = utilisateurRepository.countNumberOfRegisterPeerMonth();
        assertThat(utilisateurList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(16)
    public void getNumberTotalOfRegisterPeerYearTest() {
        List<?> utilisateurList = utilisateurRepository.countNumberOfRegisterPeerYear();
        assertThat(utilisateurList.size()).isGreaterThanOrEqualTo(0);
    }


    @Test
    @Order(17)
    @Rollback(value = false)
    public void deleteUtilisateurTest() {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(1L);
        if (optionalUtilisateur.isPresent()) {
            utilisateurRepository.delete(optionalUtilisateur.get());
        }
        Utilisateur utilisateur = null;
        Optional<Utilisateur> optionalUtilisateur1 = utilisateurRepository.findById(1L);
        if (optionalUtilisateur1.isPresent()) {
            utilisateur = optionalUtilisateur1.get();
        }
        Assertions.assertThat(utilisateur).isNull();
    }
}
