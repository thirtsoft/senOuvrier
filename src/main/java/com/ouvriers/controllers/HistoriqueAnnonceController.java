package com.ouvriers.controllers;

import com.ouvriers.controllers.api.HistoriqueAnnonceApi;
import com.ouvriers.models.HistoriqueAnnonce;
import com.ouvriers.services.HistoriqueAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HistoriqueAnnonceController implements HistoriqueAnnonceApi {

    private final HistoriqueAnnonceService historiqueAnnonceService;

    @Autowired
    public HistoriqueAnnonceController(HistoriqueAnnonceService historiqueAnnonceService) {
        this.historiqueAnnonceService = historiqueAnnonceService;
    }

    @Override
    public ResponseEntity<HistoriqueAnnonce> createHistoriqueAnnocne(HistoriqueAnnonce historiqueAnnonce) {
        HistoriqueAnnonce historiqueAnnonceResultDto = historiqueAnnonceService.save(historiqueAnnonce);
        return new ResponseEntity<>(historiqueAnnonceResultDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueAnnonce> updateHistoriqueAnnonce(Long idHistoriqueAnnonce, HistoriqueAnnonce historiqueAnnonce) {
        historiqueAnnonce.setId(idHistoriqueAnnonce);
        HistoriqueAnnonce historiqueAnnonceResultDto = historiqueAnnonceService.save(historiqueAnnonce);
        return new ResponseEntity<>(historiqueAnnonceResultDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HistoriqueAnnonce> getHistoriqueAnnonceById(Long idHistoriqueAnnonce) {
        HistoriqueAnnonce historiqueAnnonceResultDto = historiqueAnnonceService.findById(idHistoriqueAnnonce);
        return new ResponseEntity<>(historiqueAnnonceResultDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueAnnonce>> getAllHistoriqueAnnonces() {
        List<HistoriqueAnnonce> historiqueAnnonceResultDto = historiqueAnnonceService.findAll();
        return new ResponseEntity<>(historiqueAnnonceResultDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueAnnonce>> getAllHistoriqueAnnoncesOrderByIdDesc() {
        List<HistoriqueAnnonce> historiqueAnnonceResultDto = historiqueAnnonceService.findHistoriqueAnnonceByOrderByIdDesc();
        return new ResponseEntity<>(historiqueAnnonceResultDto, HttpStatus.OK);
    }

    @Override
    public long getNumbersOfhistoriqueAnnonces() {
        return historiqueAnnonceService.countNumbersOfHistoriqueAnnonces();
    }

    @Override
    public void deleteHistoriqueAnnonce(Long idHistoriqueAnnonce) {
        historiqueAnnonceService.delete(idHistoriqueAnnonce);
    }
}
