package com.ouvriers.controllers;

import com.ouvriers.controllers.api.MetierApi;
import com.ouvriers.models.Metier;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.services.MetierService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class MetierController implements MetierApi {

    private final MetierService metierService;

    @Autowired
    ServletContext context;

    @Override
    public ResponseEntity<Metier> save(Metier metier) {
        Metier metierResult = metierService.save(metier);
        return new ResponseEntity<>(metierResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Metier> update(Long id, Metier metier) {
        metier.setId(id);
        Metier metierResult = metierService.save(metier);
        return new ResponseEntity<>(metierResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Metier> getMetierById(Long id) {
        Metier metierResult = metierService.findById(id);
        return new ResponseEntity<>(metierResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Metier>> getAllMetiers() {
        List<Metier> metierResults = metierService.findAll();
        return new ResponseEntity<>(metierResults, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Metier>> getAllMetiersOrderByIdDesc() {
        List<Metier> metierResults = metierService.findByMetierByIdDesc();
        return new ResponseEntity<>(metierResults, HttpStatus.OK);
    }


    @Override
    public long getNumbersOfMetiers() {
        return metierService.countNumbersOfMetiers();
    }

    @Override
    public byte[] getPhotoMetier(Long id) throws Exception {
        Metier metier = metierService.findById(id);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") +
                "/senouvrier//ouvrier//photos/" + metier.getPhotoMetier()));
    }

    @Override
    public byte[] getPhotoMetierInContextFolder(Long id) throws Exception {
        Metier metier = metierService.findById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Metiers/photos/") + metier.getPhotoMetier()));
    }

    @Override
    public void delete(Long idMetier) {
        metierService.delete(idMetier);
    }


}
