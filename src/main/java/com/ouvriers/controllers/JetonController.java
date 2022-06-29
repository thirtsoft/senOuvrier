package com.ouvriers.controllers;

import com.ouvriers.controllers.api.JetonApi;
import com.ouvriers.models.Jeton;
import com.ouvriers.services.JetonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class JetonController implements JetonApi {

    private final JetonService jetonService;

    @Autowired
    public JetonController(JetonService jetonService) {
        this.jetonService = jetonService;
    }

    @Override
    public ResponseEntity<Jeton> createJeton(Jeton jeton) {
        jeton.setEtat("ENCOURS");
        jeton.setCreatedDate(new Date());
        Jeton newJetonDto = jetonService.save(jeton);
        return new ResponseEntity<>(newJetonDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Jeton> updateJeton(Long idJeton, Jeton jeton) {
        jeton.setId(idJeton);
        jeton.setCreatedDate(new Date());
        Jeton newJetonDto = jetonService.save(jeton);
        return new ResponseEntity<>(newJetonDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Jeton> updateEtatOfJeton(String etat, String id) {
        Jeton newAnnonceDto = jetonService.updateEtatOfJetonDto(etat, id);
        return new ResponseEntity<>(newAnnonceDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Jeton> getJetonById(Long idJeton) {
        Jeton newJetonDto = jetonService.findById(idJeton);
        return new ResponseEntity<>(newJetonDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Jeton>> getAllJetons() {
        List<Jeton> jetonList = jetonService.findAll();
        return new ResponseEntity<>(jetonList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Jeton>> getAllJetonOrderByIdDesc() {
        List<Jeton> jetonList = jetonService.findAllJetonsByOrderByIdDesc();
        return new ResponseEntity<>(jetonList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Jeton>> getAllJetonByCustomerIdOrderIdDesc(Long userId) {
        List<Jeton> jetonList = jetonService.FindListJetonByCustomerId(userId);
        return new ResponseEntity<>(jetonList, HttpStatus.OK);
    }

    @Override
    public long getNumbersOfjetons() {
        return jetonService.countNumbersOfJetons();
    }

    @Override
    public void delete(Long idJeton) {
        jetonService.delete(idJeton);
    }
}
