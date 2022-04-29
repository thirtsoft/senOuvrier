package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Locality;
import com.ouvriers.repository.LocalityRepository;
import com.ouvriers.services.LocalityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;

    @Override
    public Locality save(Locality locality) {
        return localityRepository.save(locality);
    }

    @Override
    public Locality update(Long locId, Locality locality) {
        if (!localityRepository.existsById(locId)) {
            throw new ResourceNotFoundException("Jeton not found");
        }

        Optional<Locality> optionalLocality = localityRepository.findById(locId);

        Locality localityResult = optionalLocality.get();
        localityResult.setCodePostal(locality.getCodePostal());
        localityResult.setRue(locality.getRue());
        localityResult.setName(locality.getName());
        localityResult.setAddress(locality.getAddress());

        return localityRepository.save(localityResult);

    }

    @Override
    public Locality findById(Long id) {
        if (id == null) {
            log.error("Locality not found");
            return null;
        }

        Optional<Locality> optionalJLocality = localityRepository.findById(id);

        return optionalJLocality.get();
    }

    @Override
    public List<Locality> findAllLocalitiesByAddressCode(String code) {
        if (code == null) {
            log.error("Locality not found");
            return null;
        }
        return localityRepository.findAllLocalityByAddressCode(code);
    }

    @Override
    public List<Locality> findAll() {
       return localityRepository.findAll();
    }

    @Override
    public List<Locality> findByLocalityByIdDesc() {
        return localityRepository.findByOrderByIdDesc();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Locality not found");
            return;
        }
        localityRepository.deleteById(id);
    }
}
