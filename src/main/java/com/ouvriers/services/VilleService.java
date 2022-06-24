package com.ouvriers.services;

import com.ouvriers.models.Ville;

import java.util.List;

public interface VilleService {

    Ville save(Ville ville);

    Ville update(Long idVille, Ville ville);

    Ville findById(Long id);

    List<Ville> findAll();

    List<Ville> findByVillesByIdDesc();

    void delete(Long id);


}
