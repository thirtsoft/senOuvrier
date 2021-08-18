package com.ouvriers.services.Impl;

import com.ouvriers.dtos.WhistListDto;
import com.ouvriers.repository.WhistListRepository;
import com.ouvriers.services.WhistListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WhistListServiceImpl implements WhistListService {

    private final WhistListRepository whistListRepository;

    @Override
    public WhistListDto save(WhistListDto whistListDto) {
        return null;
    }

    @Override
    public WhistListDto update(Long idVille, WhistListDto whistListDto) {
        return null;
    }

    @Override
    public WhistListDto findById(Long id) {
        return null;
    }

    @Override
    public List<WhistListDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
