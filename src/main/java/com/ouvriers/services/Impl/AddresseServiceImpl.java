package com.ouvriers.services.Impl;

import com.ouvriers.dtos.AddresseDto;
import com.ouvriers.repository.AddresseRepository;
import com.ouvriers.services.AddresseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AddresseServiceImpl implements AddresseService {

    private final AddresseRepository addresseRepository;

    @Override
    public AddresseDto save(AddresseDto addresseDto) {
        return null;
    }

    @Override
    public AddresseDto update(Long idAddress, AddresseDto addresseDto) {
        return null;
    }

    @Override
    public AddresseDto findById(Long id) {
        return null;
    }

    @Override
    public List<AddresseDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
