package com.ouvriers.services.Impl;

import com.ouvriers.dtos.TarifDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Tarif;
import com.ouvriers.repository.TarifRepository;
import com.ouvriers.services.TarifService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TarifServiceImpl implements TarifService {

    private final TarifRepository tarifRepository;

    @Override
    public TarifDto save(TarifDto tarifDto) {
        return TarifDto.fromEntityToDto(
                tarifRepository.save(
                        TarifDto.fromDtoToEntity(tarifDto)
                )
        );
    }

    @Override
    public TarifDto update(Long idTarif, TarifDto tarifDto) {
        if (!tarifRepository.existsById(idTarif)) {
            throw new ResourceNotFoundException("Tarif not found");
        }

        Optional<Tarif> optionalTarif = tarifRepository.findById(idTarif);

        if (!optionalTarif.isPresent()) {
            throw new ResourceNotFoundException("Tarif not found");
        }

        TarifDto tarifDtoResult = TarifDto.fromEntityToDto(optionalTarif.get());
        tarifDtoResult.setReference(tarifDto.getReference());
        tarifDtoResult.setMontantTarif(tarifDto.getMontantTarif());
        tarifDtoResult.setDescription(tarifDto.getDescription());
        tarifDtoResult.setAnnonceDto(tarifDto.getAnnonceDto());

        return TarifDto.fromEntityToDto(
                tarifRepository.save(
                        TarifDto.fromDtoToEntity(tarifDtoResult)
                )
        );
    }

    @Override
    public TarifDto findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Tarif> optionalTarif = tarifRepository.findById(id);

        return Optional.of(TarifDto.fromEntityToDto(optionalTarif.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Tarif avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<TarifDto> findAll() {
        return tarifRepository.findAll()
                .stream()
                .map(TarifDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TarifDto> findByTarifByIdDesc() {
        return tarifRepository.findTarifByOrderByIdDesc()
                .stream()
                .map(TarifDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TarifDto> findListTarifDtoByKeyword(String keyword) {
        return tarifRepository.findTarifByKeyword(keyword)
                .stream()
                .map(TarifDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TarifDto> findListTarifDtoByAnnonce(Long pId) {
        return tarifRepository.findTarifByAnnonce(pId)
                .stream()
                .map(TarifDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TarifDto> findTarifByPageable(Pageable pageable) {
        return tarifRepository.findTarif(pageable)
                .map(TarifDto::fromEntityToDto);
    }

    @Override
    public Page<TarifDto> findTarifByAnnonceByPageable(Long annonceId, Pageable pageable) {
        return tarifRepository.findTarifByAnnoncePageables(annonceId, pageable)
                .map(TarifDto::fromEntityToDto);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Tarif Id is null");
            return;
        }
        tarifRepository.deleteById(id);
    }
}
