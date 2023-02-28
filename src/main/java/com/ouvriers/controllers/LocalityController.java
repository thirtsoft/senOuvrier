package com.ouvriers.controllers;

import com.ouvriers.controllers.api.LocalityApi;
import com.ouvriers.models.Locality;
import com.ouvriers.services.LocalityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class LocalityController implements LocalityApi {

    private final LocalityService localityService;


    @Override
    public ResponseEntity<Locality> saveLocality(Locality locality) {
        Locality localityResult = localityService.save(locality);
        return new ResponseEntity<>(localityResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Locality> updateLocality(Long locId, Locality locality) {
        locality.setId(locId);
        Locality localityResult = localityService.save(locality);
        return new ResponseEntity<>(localityResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Locality> getLocalityById(Long locId) {
        Locality localityResult = localityService.findById(locId);
        return new ResponseEntity<>(localityResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Locality>> getAllLocalities() {
        List<Locality> localityResult = localityService.findAll();
        return new ResponseEntity<>(localityResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Locality>> getAllLocalitiesOrderByIdDesc() {
        List<Locality> localityResult = localityService.findByLocalityByIdDesc();
        return new ResponseEntity<>(localityResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Locality>> getAllLocalityByCountryCode(String code) {
        List<Locality> localityResult = localityService.findAllLocalitiesByAddressCode(code);
        return new ResponseEntity<>(localityResult, HttpStatus.OK);
    }

    @Override
    public void deleteState(Long stateId) {
        localityService.delete(stateId);
    }
}
