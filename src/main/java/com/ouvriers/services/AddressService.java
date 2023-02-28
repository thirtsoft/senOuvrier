package com.ouvriers.services;

import com.ouvriers.models.Address;

import java.util.List;

public interface AddressService {

    Address save(Address address);

    Address update(Long idAddress, Address address);

    Address findById(Long id);

    List<Address> findAll();

    List<Address> findByAddresseByIdDesc();

    void delete(Long id);

}
