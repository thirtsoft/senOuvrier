package com.ouvriers.services;

import com.ouvriers.enums.RoleName;
import com.ouvriers.models.Utilisateur;

import java.math.BigDecimal;
import java.util.List;

public interface UtilisateurService {

    Utilisateur save(Utilisateur utilisateur);

    Utilisateur update(Long id, Utilisateur utilisateur);

    void addRoleToUser(String username, RoleName roleName);

    Utilisateur findById(Long id);

    Utilisateur findByUsername(String username);

    boolean updateUsernameOfUtilisateurByUsername(String username, String newUsername);

    boolean updateUsernameOfUtilisateurByUserId(String id, String newUsername);

    boolean updateCustomerPasswordByUsername(String username, String oldPass, String newPass);

    boolean updateCustomerPasswordByUserId(String id, String oldPass, String newPass);

    boolean updateCustomerProfileByUsername(String username, String name, String newUsername, String email, String mobile);

    Utilisateur activatedUser(String isActive, String id);

    long countNumberOfRegister();

    BigDecimal countNumberOfRegisterInMonth();

    List<Utilisateur> findAll();

    List<Utilisateur> findByOrderByIdDesc();

    List<Utilisateur> findNewsRegisterInMonthByOrderByIdDesc();

    void delete(Long id);


}
