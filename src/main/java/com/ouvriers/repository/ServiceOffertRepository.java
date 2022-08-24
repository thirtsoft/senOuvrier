package com.ouvriers.repository;

import com.ouvriers.models.Rating;
import com.ouvriers.models.ServiceOffert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ServiceOffertRepository extends JpaRepository<ServiceOffert, Long> {


    List<ServiceOffert> findByOrderByIdDesc();

    @Query("select count(c) from ServiceOffert c where c.ouvrier.reference =:ouv")
    BigDecimal countNumberOfServiceByOuvrierId(@Param("ouv") String ouvRef);

    @Query("select n from ServiceOffert n where n.ouvrier.id =:num")
    List<ServiceOffert> findTop4ServiceOffertOrderByCreatedDateDesc(@Param("num") Long ouvRef);

    @Query("select n from ServiceOffert n where n.ouvrier.id =:ouv")
    List<ServiceOffert> findAllServiceOffertByOuvrierID(@Param("ouv") Long ouvRef);
}
