package com.ouvriers.services;

import com.ouvriers.models.Appointment;
import com.ouvriers.models.Prestation;

import java.math.BigDecimal;
import java.util.List;

public interface PrestationService {

    Prestation save(Prestation prestation);

    Prestation update(Long id, Prestation prestation);

    BigDecimal countNumberOfPrestationsByOuvrierId(Long idOuv);

    Prestation findById(Long id);

    List<Prestation> findAll();

    List<Prestation> findAllPrestationsOrderByIdDesc();

    List<Prestation> FindAllPrestationsOrderByOuvrierId(Long ouvId);

    List<Prestation> findTop4PrestationsOrderByCreatedDateDesc(Long ouvRef);

    void delete(Long id);

}
