package com.ouvriers.services.Impl;

import com.ouvriers.dtos.MetierDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Metier;
import com.ouvriers.repository.MetierRepository;
import com.ouvriers.services.MetierService;
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
public class MetierServiceImpl implements MetierService {

    private final MetierRepository metierRepository;


    @Override
    public MetierDto save(MetierDto metierDto) {
        return MetierDto.fromEntityToDto(
                metierRepository.save(
                        MetierDto.fromDtoToEntity(metierDto)
                )
        );
    }

    @Override
    public MetierDto update(Long idMetier, MetierDto metierDto) {
        if (!metierRepository.existsById(idMetier)) {
            throw new ResourceNotFoundException("Metier not found");
        }

        Optional<Metier> optionalMetier = metierRepository.findById(idMetier);

        if (!optionalMetier.isPresent()) {
            throw new ResourceNotFoundException("Metier not found");
        }

        MetierDto metierDtoResult = MetierDto.fromEntityToDto(optionalMetier.get());
        metierDtoResult.setReference(metierDto.getReference());
        metierDtoResult.setDesignation(metierDto.getDesignation());
        metierDtoResult.setDescription(metierDto.getDescription());

        return MetierDto.fromEntityToDto(
                metierRepository.save(
                        MetierDto.fromDtoToEntity(metierDtoResult)
                )
        );
    }

    @Override
    public MetierDto findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Metier> optionalMetier = metierRepository.findById(id);

        return Optional.of(MetierDto.fromEntityToDto(optionalMetier.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Metier avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public MetierDto findByReference(String reference) {
        if (reference == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Metier> optionalMetier = metierRepository.findByReference(reference);

        return Optional.of(MetierDto.fromEntityToDto(optionalMetier.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Metier avec l'Id = " + reference + "n'a été trouvé")
        );
    }

    @Override
    public MetierDto findByDesignation(String designation) {
        if (designation == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Metier> optionalMetier = metierRepository.findByDesignation(designation);

        return Optional.of(MetierDto.fromEntityToDto(optionalMetier.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Metier avec l'Id = " + designation + "n'a été trouvé")
        );
    }

    @Override
    public List<MetierDto> findAll() {
        return metierRepository.findAll().stream()
                .map(MetierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MetierDto> findListMetierByReference(String keyword) {
        return metierRepository.findListMetierByReference(keyword)
                .stream()
                .map(MetierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MetierDto> findByMetierByIdDesc() {
        return metierRepository.findListOfMetierByOrderByIdDesc().stream()
                .map(MetierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public BigDecimal countNumbersOfMetiers() {
        return metierRepository.countNumberOfMetiers();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return;
        }
        metierRepository.deleteById(id);

    }
}
