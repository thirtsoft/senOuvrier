package com.ouvriers.services.Impl;

import com.ouvriers.dtos.HistoriqueAnnonceDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.HistoriqueAnnonce;
import com.ouvriers.repository.HistoriqueAnnonceRepository;
import com.ouvriers.services.HistoriqueAnnonceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class HistoriqueAnnonceServiceImpl implements HistoriqueAnnonceService {

    private final HistoriqueAnnonceRepository historiqueAnnonceRepository;

    public HistoriqueAnnonceServiceImpl(HistoriqueAnnonceRepository historiqueAnnonceRepository) {
        this.historiqueAnnonceRepository = historiqueAnnonceRepository;
    }

    @Override
    public HistoriqueAnnonceDto save(HistoriqueAnnonceDto historiqueAnnonceDto) {
        return HistoriqueAnnonceDto.fromEntityToDto(
                historiqueAnnonceRepository.save(
                        HistoriqueAnnonceDto.fromDtoToEntity(historiqueAnnonceDto)
                )
        );
    }

    @Override
    public HistoriqueAnnonceDto update(Long id, HistoriqueAnnonceDto historiqueAnnonceDto) {
        if (!historiqueAnnonceRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueAnnonce not found");
        }

        Optional<HistoriqueAnnonce> optionalHistoriqueAnnonce = historiqueAnnonceRepository.findById(id);

        if (!optionalHistoriqueAnnonce.isPresent()) {
            throw new ResourceNotFoundException("HistoriqueAnnonceDto not found");
        }
        HistoriqueAnnonceDto historiqueAnnonceDtoResult = HistoriqueAnnonceDto.fromEntityToDto(optionalHistoriqueAnnonce.get());
        historiqueAnnonceDtoResult.setAction(historiqueAnnonceDto.getAction());
        historiqueAnnonceDtoResult.setCreatedDate(historiqueAnnonceDto.getCreatedDate());
        historiqueAnnonceDtoResult.setAnnonceDto(historiqueAnnonceDto.getAnnonceDto());

        return HistoriqueAnnonceDto.fromEntityToDto(
                historiqueAnnonceRepository.save(
                        HistoriqueAnnonceDto.fromDtoToEntity(historiqueAnnonceDtoResult)
                )
        );
    }

    @Override
    public HistoriqueAnnonceDto findById(Long id) {
        if (id == null) {
            log.error("HistoriqueAnnonce Id is null");
            return null;
        }

        Optional<HistoriqueAnnonce> optionalHistoriqueAnnonce = historiqueAnnonceRepository.findById(id);

        return Optional.of(HistoriqueAnnonceDto.fromEntityToDto(optionalHistoriqueAnnonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun HistoriqueAnnonce avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<HistoriqueAnnonceDto> findAll() {
        return historiqueAnnonceRepository.findAll().stream()
                .map(HistoriqueAnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoriqueAnnonceDto> findHistoriqueAnnonceByOrderByIdDesc() {
        return historiqueAnnonceRepository.findHistoriqueAnnonceByOrderByIdDesc().stream()
                .map(HistoriqueAnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumbersOfHistoriqueAnnonces() {
        return historiqueAnnonceRepository.countNumberOfHistoriqueAnnonces();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("HistoriqueAnnonce not found");
            return;
        }
        historiqueAnnonceRepository.deleteById(id);

    }
}
