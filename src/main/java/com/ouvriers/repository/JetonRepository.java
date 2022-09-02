package com.ouvriers.repository;

import com.ouvriers.models.Jeton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JetonRepository extends JpaRepository<Jeton, Long> {


    @Query("select sum(c.montant) from Jeton c where year(c.createdDate) = year(current_date) ")
    BigDecimal sumTotalOfJetonInYear();

    List<Jeton> findListOfJetonByOrderByIdDesc();

    @Query("select p from Jeton p where p.utilisateur.id =:user order by id Desc")
    List<Jeton> FindListJetonByCustomerId(@Param("user") Long userId);

    @Query("select EXTRACT(month from(c.createdDate)), sum(c.montant) from Jeton c group by EXTRACT(month from(c.createdDate))")
    List<?> sumTotalOfJetonPeerMonth();

    @Query("select EXTRACT(year from(c.createdDate)), sum(c.montant) from Jeton c group by EXTRACT(year from(c.createdDate))")
    List<?> sumTotalOfJetonPeerYear();

}

