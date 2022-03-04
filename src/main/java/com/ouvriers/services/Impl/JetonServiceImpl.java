package com.ouvriers.services.Impl;

import com.ouvriers.dtos.JetonDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Jeton;
import com.ouvriers.repository.JetonRepository;
import com.ouvriers.services.JetonService;
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
public class JetonServiceImpl implements JetonService {

    private final JetonRepository jetonRepository;

    public JetonServiceImpl(JetonRepository jetonRepository) {
        this.jetonRepository = jetonRepository;
    }

    @Override
    public JetonDto save(JetonDto jetonDto) {
        return JetonDto.fromEntityToDto(
                jetonRepository.save(
                        JetonDto.fromDtoToEntity(jetonDto)
                )
        );
    }

    @Override
    public JetonDto update(Long id, JetonDto jetonDto) {
        if (!jetonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Jeton not found");
        }

        Optional<Jeton> optionalJeton = jetonRepository.findById(id);

        JetonDto jetonDtoResult = JetonDto.fromEntityToDto(optionalJeton.get());
        jetonDtoResult.setMontant(jetonDto.getMontant());
        jetonDtoResult.setEtat(jetonDto.getEtat());
        jetonDtoResult.setCreatedDate(jetonDto.getCreatedDate());
        jetonDtoResult.setUtilisateurDto(jetonDto.getUtilisateurDto());

        return JetonDto.fromEntityToDto(
                jetonRepository.save(
                        JetonDto.fromDtoToEntity(jetonDtoResult)
                )
        );
    }

    @Override
    public JetonDto findById(Long id) {
        if (id == null) {
            log.error("Jeton not found");
            return null;
        }

        Optional<Jeton> optionalJeton = jetonRepository.findById(id);

        return Optional.of(JetonDto.fromEntityToDto(optionalJeton.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Jeton avec l'Id = " + id + "n'a été trouvé")
        );

    }

    @Override
    public List<JetonDto> findAll() {
        return jetonRepository.findAll().stream()
                .map(JetonDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JetonDto> findAllJetonsByOrderByIdDesc() {
        return jetonRepository.findListOfJetonByOrderByIdDesc().stream()
                .map(JetonDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumbersOfJetons() {
        return jetonRepository.countNumberOfJetons();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Jeton not found");
            return;
        }
        jetonRepository.deleteById(id);
    }
}
