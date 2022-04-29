package com.ouvriers.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ouvriers.controllers.api.UtilisateurApi;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;


    @Override
    public ResponseEntity<Utilisateur> save(Utilisateur Utilisateur) {
        return null;
    }

    @Override
    public ResponseEntity<Utilisateur> getUtilisateurById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return null;
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateursOrderByIdDesc() {
        return null;
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllNewsRegistersOrderByIdDesc() {
        return null;
    }

    @Override
    public ResponseEntity<Utilisateur> getUtilisateurByUsername(String username) {
        return null;
    }

    @Override
    public byte[] getPhoto(Long id) throws Exception {
        return new byte[0];
    }

    @Override
    public void uploadUserPhoto(MultipartFile file, Long id) throws IOException {

    }

    @Override
    public ResponseEntity<Utilisateur> updateUtilisateur(Long idUser, Utilisateur utilisateur) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateUserProfil(ObjectNode json) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateUsername(ObjectNode json) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateUsernameByUserId(ObjectNode json) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updatePasswordByUsername(ObjectNode json) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updatePasswordByUserId(ObjectNode json) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateCustomerProfileByUsername(ObjectNode json) {
        return null;
    }

    @Override
    public ResponseEntity<?> activatedUser(String isActive, String id) {
        return null;
    }

    @Override
    public BigDecimal getNumberOfRecruteurs() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
