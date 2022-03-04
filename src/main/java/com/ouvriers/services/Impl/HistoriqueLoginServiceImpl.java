package com.ouvriers.services.Impl;

import com.ouvriers.dtos.HistoriqueLoginDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.HistoriqueLogin;
import com.ouvriers.repository.HistoriqueLoginRepository;
import com.ouvriers.services.HistoriqueLoginService;
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
public class HistoriqueLoginServiceImpl implements HistoriqueLoginService {

    private final HistoriqueLoginRepository historiqueLoginRepository;

    public HistoriqueLoginServiceImpl(HistoriqueLoginRepository historiqueLoginRepository) {
        this.historiqueLoginRepository = historiqueLoginRepository;
    }

    @Override
    public HistoriqueLoginDto save(HistoriqueLoginDto historiqueLoginDto) {
        return HistoriqueLoginDto.fromEntityToDto(
                historiqueLoginRepository.save(
                        HistoriqueLoginDto.fromDtoToEntity(historiqueLoginDto)
                )
        );
    }

    @Override
    public HistoriqueLoginDto update(Long idLogin, HistoriqueLoginDto historiqueLoginDto) {
        if (!historiqueLoginRepository.existsById(idLogin)) {
            throw new ResourceNotFoundException("HistoriqueLogin not found");
        }

        Optional<HistoriqueLogin> optionalHistoriqueLogin = historiqueLoginRepository.findById(idLogin);

        if (optionalHistoriqueLogin.isPresent()) {
            throw new ResourceNotFoundException("HistoriqueLogin not found");
        }

        HistoriqueLoginDto historiqueLoginDtoResult = HistoriqueLoginDto.fromEntityToDto(optionalHistoriqueLogin.get());
        historiqueLoginDtoResult.setAction(historiqueLoginDto.getAction());
        historiqueLoginDtoResult.setCreatedDate(historiqueLoginDto.getCreatedDate());
        historiqueLoginDtoResult.setUtilisateurDto(historiqueLoginDto.getUtilisateurDto());

        return HistoriqueLoginDto.fromEntityToDto(
                historiqueLoginRepository.save(
                        HistoriqueLoginDto.fromDtoToEntity(historiqueLoginDtoResult)
                )
        );
    }

    @Override
    public HistoriqueLoginDto findById(Long id) {
        if (id == null) {
            log.error("HistoriqueLogin not found");
            return null;
        }

        Optional<HistoriqueLogin> optionalHistoriqueLogin = historiqueLoginRepository.findById(id);

        return Optional.of(HistoriqueLoginDto.fromEntityToDto(optionalHistoriqueLogin.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun HistoriqueLogin avec l'Id = " + id + "n'a été trouvé")
        );

    }

    @Override
    public List<HistoriqueLoginDto> findAll() {
        return historiqueLoginRepository.findAll().stream()
                .map(HistoriqueLoginDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoriqueLoginDto> findHistoriqueLoginByOrderByIdDesc() {
        return historiqueLoginRepository.findHistoriqueLoginByOrderByIdDesc().stream()
                .map(HistoriqueLoginDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumbersOfHistoriqueLogins() {
        return historiqueLoginRepository.countNumberOfHistoriqueLogins();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("HistoriqueLogin not found");
            return;
        }
        historiqueLoginRepository.deleteById(id);
    }
}
