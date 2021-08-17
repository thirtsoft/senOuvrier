package com.ouvriers.repository;

import com.ouvriers.models.WhistList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhistListRepository extends JpaRepository<WhistList, Long> {
}
