package com.ouvriers.services;

import com.ouvriers.models.HistoriqueAnnonce;

import java.util.List;

public interface HistoriqueAnnonceService {

    HistoriqueAnnonce save(HistoriqueAnnonce historiqueAnnonce);

    HistoriqueAnnonce update(Long id, HistoriqueAnnonce historiqueAnnonce);

    HistoriqueAnnonce findById(Long id);

    List<HistoriqueAnnonce> findAll();

    List<HistoriqueAnnonce> findHistoriqueAnnonceByOrderByIdDesc();

    long countNumbersOfHistoriqueAnnonces();

    void delete(Long id);
}
