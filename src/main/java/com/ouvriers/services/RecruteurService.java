package com.ouvriers.services;

import com.ouvriers.models.Recruteur;

import java.math.BigDecimal;
import java.util.List;

public interface RecruteurService {

    Recruteur save(Recruteur recruteur);

    Recruteur update(Long idRecruteur, Recruteur recruteur);

    Recruteur findById(Long id);

    BigDecimal countNumbersOfRecruteurs();

    List<Recruteur> findAll();

    void delete(Long id);


}
