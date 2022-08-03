package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.ServiceOffert;
import com.ouvriers.models.Tarif;
import com.ouvriers.repository.ServiceOffertRepository;
import com.ouvriers.repository.TarifRepository;
import com.ouvriers.services.ServiceOffertService;
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
public class ServiceOffertServiceImpl implements ServiceOffertService {

    private final ServiceOffertRepository serviceOffertRepository;

    @Override
    public ServiceOffert save(ServiceOffert serviceOffert) {
        return serviceOffertRepository.save(serviceOffert);
    }

    @Override
    public ServiceOffert update(Long idServiceOffert, ServiceOffert serviceOffert) {
        if (!serviceOffertRepository.existsById(idServiceOffert)) {
            throw new ResourceNotFoundException("ServiceOffert not found");
        }

        Optional<ServiceOffert> optionalServiceOffert = serviceOffertRepository.findById(idServiceOffert);

        if (!optionalServiceOffert.isPresent()) {
            throw new ResourceNotFoundException("ServiceOffert not found");
        }

        ServiceOffert serviceOffertResult = optionalServiceOffert.get();
        serviceOffertResult.setReference(serviceOffert.getReference());
        serviceOffertResult.setDesignation(serviceOffert.getDesignation());
        serviceOffertResult.setDescription(serviceOffert.getDescription());
        serviceOffertResult.setOuvrier(serviceOffert.getOuvrier());

        return serviceOffertRepository.save(serviceOffertResult);
    }

    @Override
    public ServiceOffert findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<ServiceOffert> optionalServiceOffert = serviceOffertRepository.findById(id);

        return Optional.of(optionalServiceOffert.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Tarif avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<ServiceOffert> findAll() {
        return serviceOffertRepository.findAll();
    }

    @Override
    public List<ServiceOffert> findByServiceOffertByIdDesc() {
        return serviceOffertRepository.findByOrderByIdDesc();
    }

    @Override
    public List<ServiceOffert> findListServiceOffertByKeyword(String keyword) {
        return null;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Tarif Id is null");
            return;
        }
        serviceOffertRepository.deleteById(id);
    }
}
