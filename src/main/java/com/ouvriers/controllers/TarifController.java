package com.ouvriers.controllers;

import com.ouvriers.controllers.api.TarifApi;
import com.ouvriers.dtos.TarifDto;
import com.ouvriers.services.TarifService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TarifController implements TarifApi {

    private final TarifService tarifService;

    @Override
    public ResponseEntity<TarifDto> save(TarifDto tarifDto) {
        return null;
    }

    @Override
    public ResponseEntity<TarifDto> update(Long id, TarifDto tarifDto) {
        return null;
    }

    @Override
    public ResponseEntity<TarifDto> getTarifById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<TarifDto> getTarifByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<TarifDto>> getAllTarifs() {
        return null;
    }

    @Override
    public ResponseEntity<List<TarifDto>> getListOfTarifsByKeyword(String keyword) {
        return null;
    }

    @Override
    public Page<TarifDto> getListTarifByPageable(int page, int size) {
        return null;
    }

    @Override
    public Page<TarifDto> getTarifByLocalityPageables(Long addId, int page, int size) {
        return null;
    }
}
