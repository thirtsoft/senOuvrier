package com.ouvriers.repository;

import com.ouvriers.models.Jeton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JetonRepository extends JpaRepository<Jeton, Long> {

    @Query("select count(p) from Jeton p ")
    BigDecimal countNumberOfJetons();

    List<Jeton> findListOfJetonByOrderByIdDesc();

}

