package com.ouvriers.controllers;

import com.ouvriers.controllers.api.MetierApi;
import com.ouvriers.dtos.MetierDto;
import com.ouvriers.services.MetierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class MetierController implements MetierApi {

    private final MetierService metierService;

    @Override
    public ResponseEntity<MetierDto> save(MetierDto metierDto) {
        return ResponseEntity.ok(metierService.save(metierDto));
    }

    @Override
    public ResponseEntity<MetierDto> update(Long id, MetierDto metierDto) {
        metierDto.setId(id);
        return ResponseEntity.ok(metierService.save(metierDto));
    }

    @Override
    public ResponseEntity<MetierDto> getMetierById(Long id) {
        return ResponseEntity.ok(metierService.findById(id));
    }

    @Override
    public ResponseEntity<MetierDto> getMetierByRerefence(String reference) {
        return ResponseEntity.ok(metierService.findByReference("%" + reference + "%"));
    }

    @Override
    public ResponseEntity<List<MetierDto>> getAllMetiers() {
        return ResponseEntity.ok(metierService.findAll());
    }

    @Override
    public ResponseEntity<List<MetierDto>> getListOfMetiersByKeyword(String keyword) {
        return ResponseEntity.ok(metierService.findListMetierByReference("%" + keyword + "%"));
    }

    @Override
    public BigDecimal getNumbersOfMetiers() {
        return metierService.countNumbersOfMetiers();
    }

    @Override
    public Page<MetierDto> getListMetierByPageable(int page, int size) {
        return null;
    }

    @Override
    public Page<MetierDto> getMetierByLocalityPageables(Long addId, int page, int size) {
        return null;
    }
}
