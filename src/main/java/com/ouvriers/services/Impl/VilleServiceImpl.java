package com.ouvriers.services.Impl;

import com.ouvriers.dtos.VilleDto;
import com.ouvriers.repository.VilleRepository;
import com.ouvriers.services.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class VilleServiceImpl implements VilleService {

    private final VilleRepository villeRepository;

    @Override
    public VilleDto save(VilleDto villeDto) {
        return null;
    }

    @Override
    public VilleDto update(Long idVille, VilleDto villeDto) {
        return null;
    }

    @Override
    public VilleDto findById(Long id) {
        return null;
    }

    @Override
    public List<VilleDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
