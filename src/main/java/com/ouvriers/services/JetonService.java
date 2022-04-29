package com.ouvriers.services;

import com.ouvriers.models.Jeton;

import java.util.List;

public interface JetonService {

    Jeton save(Jeton jeton);

    Jeton update(Long id, Jeton jeton);

    Jeton updateEtatOfJetonDto(String etat, String id);

    Jeton findById(Long id);

    List<Jeton> findAll();

    List<Jeton> findAllJetonsByOrderByIdDesc();

    List<Jeton> FindListJetonByCustomerId(Long userId);

    long countNumbersOfJetons();

    void delete(Long id);

}
