package com.ouvriers.services.Impl;

import com.ouvriers.enums.RoleName;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Role;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.RoleRepository;
import com.ouvriers.repository.UtilisateurRepository;
import com.ouvriers.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur update(Long id, Utilisateur utilisateur) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("State not found");
        }

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (!optionalUtilisateur.isPresent()) {
            throw new ResourceNotFoundException("State not found");
        }

        Utilisateur utilisateurResult = optionalUtilisateur.get();

        utilisateurResult.setName(utilisateur.getName());
        utilisateurResult.setUsername(utilisateur.getUsername());
        utilisateurResult.setEmail(utilisateur.getEmail());
        utilisateurResult.setMobile(utilisateur.getMobile());
        utilisateurResult.setAddressRecruteur(utilisateur.getAddressRecruteur());
        utilisateurResult.setVilleRecruteur(utilisateur.getVilleRecruteur());
        utilisateurResult.setNomEntreprise(utilisateur.getNomEntreprise());
        utilisateurResult.setSecteurActivite(utilisateur.getSecteurActivite());
        utilisateurResult.setWebsite(utilisateur.getWebsite());
        utilisateurResult.setInformation(utilisateur.getInformation());

        return utilisateurRepository.save(utilisateur);

    }

    @Override
    public boolean updateUsernameOfUtilisateurByUsername(String username, String newUsername) {
        Optional<Utilisateur> existsUser = utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setUsername(newUsername);
            this.utilisateurRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateUsernameOfUtilisateurByUserId(String id, String newUsername) {
        Optional<Utilisateur> existsUser = utilisateurRepository.findById(Long.valueOf(id));
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setUsername(newUsername);
            this.utilisateurRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateCustomerPasswordByUsername(String username, String oldPass, String newPass) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();

            if (passwordEncoder.matches(oldPass, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPass));
                this.utilisateurRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCustomerPasswordByUserId(String id, String oldPass, String newPass) {
        Optional<Utilisateur> existsUser = utilisateurRepository.findById(Long.valueOf(id));
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();

            if (passwordEncoder.matches(oldPass, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPass));
                this.utilisateurRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCustomerProfileByUsername(String username, String name, String newUsername, String email, String mobile) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setName(name);
            user.setUsername(newUsername);
            user.setEmail(email);
            user.setMobile(mobile);

            utilisateurRepository.save(user);

            return true;

        }
        return false;
    }

    @Override
    public Utilisateur activatedUser(String isActive, String id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(Long.valueOf(id));
        Utilisateur utilisateur = optionalUtilisateur.get();
        utilisateur.setActive(Boolean.valueOf(isActive));

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public BigDecimal countNumberOfRegisterInMonth() {
        return utilisateurRepository.countNumberOfRegisterInMonth();
    }

    @Override
    public Utilisateur findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Utilisateur> utiliOptional = utilisateurRepository.findById(id);

        return Optional.of(utiliOptional.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun chauffeur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public Utilisateur findByUsername(String username) {
        if (username == null) {
            log.error("Utilisateur with this username is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username);

        return Optional.of(utilisateur.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Utilisateur avec l'Id = " + username + "n'a été trouvé")
        );
    }


    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    @Override
    public List<Utilisateur> findByOrderByIdDesc() {
        return utilisateurRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Utilisateur> findNewsRegisterInMonthByOrderByIdDesc() {
        return utilisateurRepository.findUtilisateursByOrderByIdDesc();
    }


    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }

    @Override
    public void addRoleToUser(String username, RoleName roleName) {
        Role role = roleRepository.findByName(roleName).get();

        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).get();

        utilisateur.getRoles().add(role);

    }

}
