package com.ouvriers.services.Impl;

import com.ouvriers.dtos.TarifDto;
import com.ouvriers.repository.TarifRepository;
import com.ouvriers.services.TarifService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TarifServiceImpl implements TarifService {

    private final TarifRepository tarifRepository;

    @Override
    public TarifDto save(TarifDto tarifDto) {
        return null;
    }

    @Override
    public TarifDto update(Long idTarif, TarifDto tarifDto) {
        return null;
    }

    @Override
    public TarifDto findById(Long id) {
        return null;
    }

    @Override
    public TarifDto findByReference(String reference) {
        return null;
    }

    @Override
    public List<TarifDto> findAll() {
        return null;
    }

    @Override
    public List<TarifDto> findListTarifDtoByKeyword(String keyword) {
        return null;
    }

    @Override
    public List<TarifDto> findListTarifDtoByAnnonce(Long pId) {
        return null;
    }

    @Override
    public Page<TarifDto> findTarifByPageable(Pageable pageable) {
        return null;
    }

    @Override
    public Page<TarifDto> findTarifByAnnonceByPageable(Long annonceId, Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
