package com.ouvriers.services;

import com.ouvriers.models.ServiceOffert;

import java.util.List;

public interface ServiceOffertService {

    ServiceOffert save(ServiceOffert serviceOffert);

    ServiceOffert update(Long idServiceOffert, ServiceOffert serviceOffert);

    ServiceOffert findById(Long id);

    List<ServiceOffert> findAll();

    List<ServiceOffert> findByServiceOffertByIdDesc();

    List<ServiceOffert> findListServiceOffertByKeyword(String keyword);

    void delete(Long id);
}
