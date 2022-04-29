package com.ouvriers.controllers;

import com.ouvriers.controllers.api.TarifApi;
import com.ouvriers.models.Tarif;
import com.ouvriers.services.TarifService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TarifController implements TarifApi {

    private final TarifService tarifService;

    @Override
    public ResponseEntity<Tarif> save(Tarif tarif) {
        Tarif tarifDtoResult = tarifService.save(tarif);
        return new ResponseEntity<>(tarifDtoResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Tarif> update(Long id, Tarif tarif) {
        tarif.setId(id);
        Tarif tarifDtoResult = tarifService.save(tarif);
        return new ResponseEntity<>(tarifDtoResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Tarif> getTarifById(Long id) {
        Tarif tarifDtoResult = tarifService.findById(id);
        return new ResponseEntity<>(tarifDtoResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Tarif>> getAllTarifs() {
        List<Tarif> tarifList = tarifService.findAll();
        return new ResponseEntity<>(tarifList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Tarif>> getAllTarifsOrderByIdDesc() {
        List<Tarif> tarifList = tarifService.findByTarifByIdDesc();
        return new ResponseEntity<>(tarifList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        tarifService.delete(id);
    }


}
