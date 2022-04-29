package com.ouvriers.controllers;

import com.ouvriers.controllers.api.HistoriqueLoginApi;
import com.ouvriers.models.HistoriqueLogin;
import com.ouvriers.services.HistoriqueLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HistoriqueLoginController implements HistoriqueLoginApi {

    private final HistoriqueLoginService historiqueLoginService;

    @Autowired
    public HistoriqueLoginController(HistoriqueLoginService historiqueLoginService) {
        this.historiqueLoginService = historiqueLoginService;
    }

    @Override
    public ResponseEntity<HistoriqueLogin> createHistoriqueLogin(HistoriqueLogin historiqueLogin) {
        HistoriqueLogin historiqueLoginResultDto = historiqueLoginService.save(historiqueLogin);
        return new ResponseEntity<>(historiqueLoginResultDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueLogin> updateHistoriqueLogin(Long idHistoriqueLogin, HistoriqueLogin historiqueLogin) {
        historiqueLogin.setId(idHistoriqueLogin);
        HistoriqueLogin historiqueLoginResultDto = historiqueLoginService.save(historiqueLogin);
        return new ResponseEntity<>(historiqueLoginResultDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HistoriqueLogin> getHistoriqueLoginById(Long idHistoriqueLogin) {
        HistoriqueLogin historiqueLoginResultDto = historiqueLoginService.findById(idHistoriqueLogin);
        return new ResponseEntity<>(historiqueLoginResultDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLogins() {
        List<HistoriqueLogin> historiqueLoginResultDtos = historiqueLoginService.findAll();
        return new ResponseEntity<>(historiqueLoginResultDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLoginOrderByIdDesc() {
        List<HistoriqueLogin> historiqueLoginResultDtos = historiqueLoginService.findHistoriqueLoginByOrderByIdDesc();
        return new ResponseEntity<>(historiqueLoginResultDtos, HttpStatus.OK);
    }

    @Override
    public long getNumbersOfHistoriqueLogins() {
        return historiqueLoginService.countNumbersOfHistoriqueLogins();
    }

    @Override
    public void deleteHistoriqueLogin(Long idHistoriqueLogin) {
        historiqueLoginService.delete(idHistoriqueLogin);
    }
}
