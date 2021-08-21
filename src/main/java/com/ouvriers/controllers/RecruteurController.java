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
    public ResponseEntity<RecruteurDto> save(RecruteurDto RecruteurDto) {
        return null;
    }

    @Override
    public ResponseEntity<RecruteurDto> update(Long id, RecruteurDto RecruteurDto) {
        return null;
    }

    @Override
    public ResponseEntity<RecruteurDto> getRecruteurById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<RecruteurDto> getRecruteurByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<RecruteurDto>> getAllRecruteurs() {
        return null;
    }

    @Override
    public ResponseEntity<List<RecruteurDto>> getListOfrecruteursByKeyword(String keyword) {
        return null;
    }

    @Override
    public BigDecimal getNumbersOfRecruteurs() {
        return null;
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
