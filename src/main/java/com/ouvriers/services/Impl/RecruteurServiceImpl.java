package com.ouvriers.services.Impl;


import com.ouvriers.models.Recruteur;
import com.ouvriers.repository.RecruteurRepository;
import com.ouvriers.services.RecruteurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RecruteurServiceImpl implements RecruteurService {

    private final RecruteurRepository recruteurRepository;


    @Override
    public Recruteur save(Recruteur recruteur) {
        return null;
    }

    @Override
    public Recruteur update(Long idRecruteur, Recruteur recruteur) {
        return null;
    }

    @Override
    public Recruteur findById(Long id) {
        return null;
    }

    @Override
    public BigDecimal countNumbersOfRecruteurs() {
        return null;
    }

    @Override
    public List<Recruteur> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
