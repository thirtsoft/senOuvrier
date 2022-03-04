package com.ouvriers.services.Impl;

import com.ouvriers.dtos.VilleDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Ville;
import com.ouvriers.repository.VilleRepository;
import com.ouvriers.services.VilleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class VilleServiceImpl implements VilleService {

    private final VilleRepository villeRepository;

    @Override
    public VilleDto save(VilleDto villeDto) {
        return VilleDto.fromEntityToDto(
                villeRepository.save(
                        VilleDto.fromDtoToEntity(villeDto)
                )
        );
    }

    @Override
    public VilleDto update(Long idVille, VilleDto villeDto) {
        if (!villeRepository.existsById(idVille)) {
            throw new ResourceNotFoundException("Ville not found");
        }

        Optional<Ville> optionalVille = villeRepository.findById(idVille);

        if (!optionalVille.isPresent()) {
            throw new ResourceNotFoundException("Ville not found");
        }

        VilleDto villeDtoResult = VilleDto.fromEntityToDto(optionalVille.get());
        villeDtoResult.setReference(villeDto.getReference());
        villeDtoResult.setNom(villeDto.getNom());
        villeDtoResult.setPays(villeDto.getPays());

        return VilleDto.fromEntityToDto(
                villeRepository.save(
                        VilleDto.fromDtoToEntity(villeDtoResult)
                )
        );
    }

    @Override
    public VilleDto findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Ville> optionalVille = villeRepository.findById(id);

        return Optional.of(VilleDto.fromEntityToDto(optionalVille.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ville avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<VilleDto> findAll() {
        return villeRepository.findAll()
                .stream()
                .map(VilleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VilleDto> findByVillesByIdDesc() {
        return villeRepository.findVilleByOrderByIdDesc()
                .stream()
                .map(VilleDto::fromEntityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Ville Id is null");
            return;
        }
        villeRepository.deleteById(id);
    }
}
