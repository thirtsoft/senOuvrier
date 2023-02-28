package com.ouvriers.services;

import com.ouvriers.models.Tarif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TarifService {

    Tarif save(Tarif tarif);

    Tarif update(Long idTarif, Tarif tarif);

    Tarif findById(Long id);

    List<Tarif> findAll();

    List<Tarif> findByTarifByIdDesc();

    List<Tarif> findListTarifByKeyword(String keyword);

    List<Tarif> findListTarifByAnnonce(Long pId);

    Page<Tarif> findTarifByPageable(Pageable pageable);

    Page<Tarif> findTarifByAnnonceByPageable(Long annonceId, Pageable pageable);

    void delete(Long id);

}
