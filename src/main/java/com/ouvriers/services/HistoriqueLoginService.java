package com.ouvriers.services;

import com.ouvriers.models.HistoriqueLogin;

import java.util.List;

public interface HistoriqueLoginService {

    HistoriqueLogin save(HistoriqueLogin historiqueLogin);

    HistoriqueLogin update(Long idLogin, HistoriqueLogin historiqueLogin);

    HistoriqueLogin findById(Long id);

    List<HistoriqueLogin> findAll();

    List<HistoriqueLogin> findHistoriqueLoginByOrderByIdDesc();

    long countNumbersOfHistoriqueLogins();

    void delete(Long id);

}
