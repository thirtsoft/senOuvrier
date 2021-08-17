package com.ouvriers.services.Impl;

import com.ouvriers.dtos.AnnonceDto;
import com.ouvriers.repository.AnnonceRepository;
import com.ouvriers.services.AnnonceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    @Override
    public AnnonceDto save(AnnonceDto annonceDto) {
        return null;
    }

    @Override
    public AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto) {
        return null;
    }

    @Override
    public AnnonceDto findById(Long id) {
        return null;
    }

    @Override
    public AnnonceDto findByReference(String reference) {
        return null;
    }

    @Override
    public List<AnnonceDto> findAll() {
        return null;
    }

    @Override
    public List<AnnonceDto> findListAnnonceByKeyword(String keyword) {
        return null;
    }

    @Override
    public List<AnnonceDto> findListAnnonceByLibelle(String libelle) {
        return null;
    }

    @Override
    public List<AnnonceDto> findListAnnonceByPermis(Long pId) {
        return null;
    }

    @Override
    public BigDecimal countNumbersOfAnnonces() {
        return null;
    }

    @Override
    public Page<AnnonceDto> findAnnonceByPageable(Pageable pageable) {
        return null;
    }

    @Override
    public Page<AnnonceDto> findAnnonceByPermisByPageable(Long permisId, Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
