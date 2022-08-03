package com.ouvriers.controllers;

import com.ouvriers.controllers.api.PrestationApi;
import com.ouvriers.models.Prestation;
import com.ouvriers.services.PrestationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class PrestationController implements PrestationApi {

    private final PrestationService prestationService;

    @Override
    public ResponseEntity<Prestation> create(Prestation prestation) {
        Prestation prestationResult = prestationService.save(prestation);
        return new ResponseEntity<>(prestationResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Prestation> update(Long id, Prestation prestation) {
        prestation.setId(id);
        Prestation prestationResult = prestationService.save(prestation);
        return new ResponseEntity<>(prestationResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Prestation> getPrestationById(Long id) {
        Prestation prestationResult = prestationService.findById(id);
        return new ResponseEntity<>(prestationResult, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfPrestationByOuvrierId(Long id) {
        return prestationService.countNumberOfPrestationsByOuvrierId(id);
    }

    @Override
    public ResponseEntity<List<Prestation>> getAllPrestations() {
        List<Prestation> prestationList = prestationService.findAll();
        return new ResponseEntity<>(prestationList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Prestation>> getAllPrestationsOrderByIdDesc() {
        List<Prestation> prestationList = prestationService.findAllPrestationsOrderByIdDesc();
        return new ResponseEntity<>(prestationList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Prestation>> getAllPrestationsByOuvrierId(Long ouvId) {
        List<Prestation> prestationList = prestationService.FindAllPrestationsOrderByOuvrierId(ouvId);
        return new ResponseEntity<>(prestationList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Prestation>> getTop4PrestationByOuvrierIdOrderByCreatedDateDesc(Long ouvId) {
        List<Prestation> prestationList = prestationService.findTop4PrestationsOrderByCreatedDateDesc(ouvId);
        return new ResponseEntity<>(prestationList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        prestationService.delete(id);

    }
}
