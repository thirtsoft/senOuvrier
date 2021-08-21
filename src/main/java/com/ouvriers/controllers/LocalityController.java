package com.ouvriers.controllers;

import com.ouvriers.controllers.api.LocalityApi;
import com.ouvriers.dtos.AddresseDto;
import com.ouvriers.services.AddresseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class LocalityController implements LocalityApi {

    private final AddresseService addresseService;

    @Override
    public ResponseEntity<AddresseDto> save(AddresseDto addresseDto) {
        return null;
    }

    @Override
    public ResponseEntity<AddresseDto> update(Long id, AddresseDto AddresseDto) {
        return null;
    }

    @Override
    public ResponseEntity<AddresseDto> getAddressById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AddresseDto> getAddressByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<AddresseDto>> getAllLocalities() {
        return null;
    }

    @Override
    public ResponseEntity<List<AddresseDto>> getListOfLocalitiesByKeyword(String keyword) {
        return null;
    }

    @Override
    public BigDecimal getNumbersOfLocalities() {
        return null;
    }

    @Override
    public Page<AddresseDto> getListAddressByPageable(int page, int size) {
        return null;
    }

    @Override
    public Page<AddresseDto> getAddressByLocalityPageables(Long addId, int page, int size) {
        return null;
    }
}
