package com.ouvriers.controllers;

import com.ouvriers.controllers.api.RecruteurApi;
import com.ouvriers.dtos.RecruteurDto;
import com.ouvriers.services.RecruteurService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class RecruteurController implements RecruteurApi {

    private final RecruteurService recruteurService;

    @Override
    public ResponseEntity<RecruteurDto> save(RecruteurDto recruteurDto) {
        return ResponseEntity.ok(recruteurService.save(recruteurDto));
    }

    @Override
    public ResponseEntity<RecruteurDto> update(Long id, RecruteurDto recruteurDto) {
        recruteurDto.setId(id);
        return ResponseEntity.ok(recruteurService.save(recruteurDto));
    }

    @Override
    public ResponseEntity<RecruteurDto> getRecruteurById(Long id) {
        return ResponseEntity.ok(recruteurService.findById(id));
    }

    @Override
    public ResponseEntity<RecruteurDto> getRecruteurByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<RecruteurDto>> getAllRecruteurs() {
        return ResponseEntity.ok(recruteurService.findAll());
    }

    @Override
    public ResponseEntity<List<RecruteurDto>> getListOfrecruteursByKeyword(String keyword) {
        return null;
    }

    @Override
    public BigDecimal getNumbersOfRecruteurs() {
        return recruteurService.countNumbersOfRecruteurs();
    }

    @Override
    public Page<RecruteurDto> getListRecruteurByPageable(int page, int size) {
        return null;
    }

    @Override
    public Page<RecruteurDto> getRecruteurByLocalityPageables(Long addId, int page, int size) {
        return null;
    }
}
