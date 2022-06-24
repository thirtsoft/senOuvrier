package com.ouvriers.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ouvriers.controllers.api.UtilisateurApi;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    @Autowired
    ServletContext context;


    @Override
    public ResponseEntity<Utilisateur> save(Utilisateur utilisateur) {
        Utilisateur utilisateurResult = utilisateurService.save(utilisateur);
        return new ResponseEntity<>(utilisateurResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Utilisateur> getUtilisateurById(Long id) {
        Utilisateur utilisateurResult = utilisateurService.findById(id);
        return new ResponseEntity<>(utilisateurResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurList = utilisateurService.findAll();
        return new ResponseEntity<>(utilisateurList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateursOrderByIdDesc() {
        List<Utilisateur> utilisateurList = utilisateurService.findByOrderByIdDesc();
        return new ResponseEntity<>(utilisateurList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllNewsRegisterInMonthOrderByIdDesc() {
        List<Utilisateur> utilisateurList = utilisateurService.findNewsRegisterInMonthByOrderByIdDesc();
        return new ResponseEntity<>(utilisateurList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Utilisateur> getUtilisateurByUsername(String username) {
        Utilisateur utilisateurResult = utilisateurService.findByUsername(username);
        return new ResponseEntity<>(utilisateurResult, HttpStatus.OK);
    }

    @Override
    public byte[] getPhoto(Long id) throws Exception {
        Utilisateur user = utilisateurService.findById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/") + user.getPhoto()));
    }

    @Override
    public void uploadUserPhoto(MultipartFile file, Long id) throws IOException {
        Utilisateur utilisateurDto = utilisateurService.findById(id);
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            utilisateurDto.setPhoto(filename);

            utilisateurService.save(utilisateurDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<Utilisateur> updateUtilisateur(Long idUser, Utilisateur utilisateur) {
        utilisateur.setId(idUser);
        Utilisateur utilisateurResult = utilisateurService.save(utilisateur);
        return new ResponseEntity<>(utilisateurResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> updateUserProfil(ObjectNode json) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateUsername(ObjectNode json) {
        String username;
        String newUsername;
        try {
            username = new ObjectMapper().treeToValue(json.get("username"), String.class);
            newUsername = new ObjectMapper().treeToValue(json.get("newUsername"), String.class);
            boolean existsUser = utilisateurService.updateUsernameOfUtilisateurByUsername(username, newUsername);
            if (existsUser)
                return new ResponseEntity<>(existsUser, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Boolean> updateUsernameByUserId(ObjectNode json) {
        String id;
        String newUsername;
        try {
            id = new ObjectMapper().treeToValue(json.get("id"), String.class);
            newUsername = new ObjectMapper().treeToValue(json.get("newUsername"), String.class);
            boolean existsUser = utilisateurService.updateUsernameOfUtilisateurByUserId(id, newUsername);
            if (existsUser)
                return new ResponseEntity<>(existsUser, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Boolean> updatePasswordByUsername(ObjectNode json) {
        String username;
        String oldPassword;
        String newPassword;
        try {
            username = new ObjectMapper().treeToValue(json.get("username"), String.class);
            oldPassword = new ObjectMapper().treeToValue(json.get("oldPassword"), String.class);
            newPassword = new ObjectMapper().treeToValue(json.get("newPassword"), String.class);

            boolean existUser = utilisateurService.updateCustomerPasswordByUsername(username, oldPassword, newPassword);
            if (existUser)
                return new ResponseEntity<>(existUser, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Boolean> updatePasswordByUserId(ObjectNode json) {
        String id;
        String oldPassword;
        String newPassword;
        try {
            id = new ObjectMapper().treeToValue(json.get("id"), String.class);
            oldPassword = new ObjectMapper().treeToValue(json.get("oldPassword"), String.class);
            newPassword = new ObjectMapper().treeToValue(json.get("newPassword"), String.class);

            boolean existUser = utilisateurService.updateCustomerPasswordByUserId(id, oldPassword, newPassword);
            if (existUser)
                return new ResponseEntity<>(existUser, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Boolean> updateCustomerProfileByUsername(ObjectNode json) {
        String username;
        String name;
        String newUsername;
        String email;
        String mobile;
        try {
            username = new ObjectMapper().treeToValue(json.get("username"), String.class);
            name = new ObjectMapper().treeToValue(json.get("name"), String.class);
            newUsername = new ObjectMapper().treeToValue(json.get("newUsername"), String.class);
            email = new ObjectMapper().treeToValue(json.get("email"), String.class);
            mobile = new ObjectMapper().treeToValue(json.get("mobile"), String.class);

            boolean existUser = utilisateurService.updateCustomerProfileByUsername(username, name, newUsername, email, mobile);
            if (existUser)
                return new ResponseEntity<>(existUser, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<?> activatedUser(String isActive, String id) {
        Utilisateur activatedUserDTO = utilisateurService.activatedUser(isActive, id);
        return new ResponseEntity<>(activatedUserDTO, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfRecruteurs() {
        return null;
    }

    @Override
    public BigDecimal getNumberOfRegisterInMonth() {
        return utilisateurService.countNumberOfRegisterInMonth();
    }

    @Override
    public void delete(Long id) {
        utilisateurService.delete(id);
    }
}
