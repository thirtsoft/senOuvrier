package com.ouvriers.repository;

import com.ouvriers.models.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {

    List<Locality> findByOrderByIdDesc();

    @Query("select p from Locality p where p.address.code =:code")
    List<Locality> findAllLocalityByAddressCode(@Param("code") String code);


}
