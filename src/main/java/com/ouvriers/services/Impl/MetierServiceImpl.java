package com.ouvriers.services.Impl;

import com.ouvriers.dtos.MetierDto;
import com.ouvriers.repository.MetierRepository;
import com.ouvriers.services.MetierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MetierServiceImpl implements MetierService {

    private final MetierRepository metierRepository;

    @Override
    public MetierDto save(MetierDto metierDto) {
        return null;
    }

    @Override
    public MetierDto update(Long idMetier, MetierDto metierDto) {
        return null;
    }

    @Override
    public MetierDto findById(Long id) {
        return null;
    }

    @Override
    public List<MetierDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
