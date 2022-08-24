package com.ouvriers.controllers;

import com.ouvriers.controllers.api.ServiceOffertApi;
import com.ouvriers.models.ServiceOffert;
import com.ouvriers.services.ServiceOffertService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class ServiceOffertController implements ServiceOffertApi {

    private final ServiceOffertService serviceOffertService;

    @Override
    public ResponseEntity<ServiceOffert> save(ServiceOffert serviceOffert) {
        ServiceOffert serviceOffertResult =serviceOffertService.save(serviceOffert);
        return new ResponseEntity<>(serviceOffertResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ServiceOffert> update(Long id, ServiceOffert serviceOffert) {
        serviceOffert.setId(id);
        ServiceOffert serviceOffertResult =serviceOffertService.save(serviceOffert);
        return new ResponseEntity<>(serviceOffertResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ServiceOffert> getServiceOffertById(Long id) {
        ServiceOffert serviceOffertResult =serviceOffertService.findById(id);
        return new ResponseEntity<>(serviceOffertResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ServiceOffert>> getAllServiceOfferts() {
        List<ServiceOffert> serviceOffertList = serviceOffertService.findAll();
        return new ResponseEntity<>(serviceOffertList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ServiceOffert>> getAllserviceOffertsOrderByIdDesc() {
        List<ServiceOffert> serviceOffertList = serviceOffertService.findByServiceOffertByIdDesc();
        return new ResponseEntity<>(serviceOffertList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ServiceOffert>> getAllServiceOffertByOuvrierId(Long ouvId) {
        List<ServiceOffert> serviceOffertList = serviceOffertService.findAllServiceOffertByOuvrierId(ouvId);
        return new ResponseEntity<>(serviceOffertList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        serviceOffertService.delete(id);
    }
}
