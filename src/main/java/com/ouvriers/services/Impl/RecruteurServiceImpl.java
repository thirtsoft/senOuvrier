package com.ouvriers.services.Impl;

import com.ouvriers.dtos.RecruteurDto;
import com.ouvriers.repository.RecruteurRepository;
import com.ouvriers.services.RecruteurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RecruteurServiceImpl implements RecruteurService {

    private final RecruteurRepository recruteurRepository;

    @Override
    public RecruteurDto save(RecruteurDto recruteurDto) {
        return null;
    }

    @Override
    public RecruteurDto update(Long idRecruteur, RecruteurDto recruteurDto) {
        return null;
    }

    @Override
    public RecruteurDto findById(Long id) {
        return null;
    }

    @Override
    public List<RecruteurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BigDecimal countNumbersOfRecruteurs() {
        return null;
    }
}
