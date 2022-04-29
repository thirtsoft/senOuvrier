package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Address;
import com.ouvriers.repository.AddressRepository;
import com.ouvriers.services.AddressService;
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
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address save(Address addresse) {
        return addressRepository.save(addresse);
    }

    @Override
    public Address update(Long idAddress, Address addresseDto) {

        if (!addressRepository.existsById(idAddress)) {
            throw new ResourceNotFoundException("Address not found");
        }

        Optional<Address> addresse = addressRepository.findById(idAddress);

        if (!addresse.isPresent()) {
            throw new ResourceNotFoundException("Address not found");
        }

        Address addressResult = addresse.get();
        addressResult.setCode(addresseDto.getCode());
        addressResult.setCountry(addresseDto.getCountry());

        return addressRepository.save(addressResult);
    }

    @Override
    public Address findById(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return null;
        }

        Optional<Address> addresse = addressRepository.findById(id);

        return Optional.of(addresse.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Addresse avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> findByAddresseByIdDesc() {
        return addressRepository.findAddresseByOrderByIdDesc();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return;
        }
        addressRepository.deleteById(id);

    }
}
