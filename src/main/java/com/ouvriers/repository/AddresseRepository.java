package com.ouvriers.repository;

import com.ouvriers.models.Addresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddresseRepository extends JpaRepository<Addresse, Long> {

    List<Addresse> findAddresseByOrderByIdDesc();

}
