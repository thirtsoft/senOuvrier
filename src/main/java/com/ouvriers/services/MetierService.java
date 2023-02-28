package com.ouvriers.services;

import com.ouvriers.models.Metier;

import java.util.List;

public interface MetierService {

    Metier save(Metier metier);

    Metier update(Long idMetier, Metier metier);

    Metier findById(Long id);

    List<Metier> findAll();

    List<Metier> findByMetierByIdDesc();

    long countNumbersOfMetiers();

    void delete(Long id);

}
