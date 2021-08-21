package com.ouvriers.services.Impl;

import com.ouvriers.dtos.AddresseDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Addresse;
import com.ouvriers.repository.AddresseRepository;
import com.ouvriers.services.AddresseService;
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
public class AddresseServiceImpl implements AddresseService {

    private final AddresseRepository addresseRepository;

    @Override
    public AddresseDto save(AddresseDto addresseDto) {
        return AddresseDto.fromEntityToDto(
                addresseRepository.save(
                        AddresseDto.fromDtoToEntity(addresseDto)
                )
        );
    }

    @Override
    public AddresseDto update(Long idAddress, AddresseDto addresseDto) {

        if (!addresseRepository.existsById(idAddress)) {
            throw new ResourceNotFoundException("Address not found");
        }

        Optional<Addresse> addresse = addresseRepository.findById(idAddress);

        if (!addresse.isPresent()) {
            throw new ResourceNotFoundException("Address not found");
        }

        AddresseDto addressDtoResult = AddresseDto.fromEntityToDto(addresse.get());
        addressDtoResult.setReference(addresseDto.getReference());
        addressDtoResult.setQuartier(addresseDto.getQuartier());
        addressDtoResult.setCodePostal(addresseDto.getCodePostal());
        addressDtoResult.setRue(addresseDto.getRue());
        addressDtoResult.setVille(addresseDto.getVille());
        addressDtoResult.setPays(addresseDto.getPays());

        return AddresseDto.fromEntityToDto(
                addresseRepository.save(
                        AddresseDto.fromDtoToEntity(addressDtoResult)
                )
        );
    }

    @Override
    public AddresseDto findById(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return null;
        }

        Optional<Addresse> addresse = addresseRepository.findById(id);

        return Optional.of(AddresseDto.fromEntityToDto(addresse.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Addresse avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<AddresseDto> findAll() {
        return addresseRepository.findAll().stream()
                .map(AddresseDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return;
        }
        addresseRepository.deleteById(id);

    }
}
