package com.ouvriers.services;

import com.ouvriers.dtos.UtilisateurDto;
import com.ouvriers.enums.RoleName;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto update(Long id, UtilisateurDto utilisateurDto);

    void addRoleToUser(String username, RoleName roleName);

    UtilisateurDto findById(Long id);

    UtilisateurDto findByUsername(String username);

    boolean updateUsernameOfUtilisateurByUsername(String username, String newUsername);

    boolean updateUsernameOfUtilisateurByUserId(String id, String newUsername);

    boolean updateCustomerPasswordByUsername(String username, String oldPass, String newPass);

    boolean updateCustomerPasswordByUserId(String id, String oldPass, String newPass);

    boolean updateCustomerProfileByUsername(String username, String name, String newUsername, String email, String mobile);

    List<UtilisateurDto> findAll();

    List<UtilisateurDto> findByOrderByIdDesc();

    void delete(Long id);


}
