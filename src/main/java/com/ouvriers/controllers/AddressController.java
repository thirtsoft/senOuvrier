package com.ouvriers.controllers;

import com.ouvriers.controllers.api.AddressApi;
import com.ouvriers.models.Address;
import com.ouvriers.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@CrossOrigin
@RestController
public class AddressController implements AddressApi {

    private final AddressService addresseService;

    @Autowired
    public AddressController(AddressService addresseService) {
        this.addresseService = addresseService;
    }

    @Override
    public ResponseEntity<Address> save(Address Address) {
        return ResponseEntity.ok(addresseService.save(Address));
    }

    @Override
    public ResponseEntity<Address> update(Long id, Address Address) {
        Address.setId(id);
        return ResponseEntity.ok(addresseService.save(Address));
    }

    @Override
    public ResponseEntity<Address> getAddressById(Long id) {
        return ResponseEntity.ok(addresseService.findById(id));
    }

    @Override
    public ResponseEntity<Address> getAddressByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<Address>> getAlladdresses() {
        return null;
    }

    @Override
    public ResponseEntity<List<Address>> getListOfaddressesByKeyword(String keyword) {
        return null;
    }

    @Override
    public BigDecimal getNumbersOfAddresses() {
        return null;
    }

    public BigDecimal getNumbersOfLocalities() {
        return null;
    }

    @Override
    public Page<Address> getListAddressByPageable(int page, int size) {
        return null;
    }

    @Override
    public Page<Address> getAddressByLocalityPageables(Long addId, int page, int size) {
        return null;
    }
}
