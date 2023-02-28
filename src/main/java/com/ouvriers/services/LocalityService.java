package com.ouvriers.services;

import com.ouvriers.models.Locality;

import java.util.List;

public interface LocalityService {

    Locality save(Locality locality);

    Locality update(Long idAddress, Locality locality);

    Locality findById(Long id);

    List<Locality> findAllLocalitiesByAddressCode(String code);

    List<Locality> findAll();

    List<Locality> findByLocalityByIdDesc();

    void delete(Long id);
}
