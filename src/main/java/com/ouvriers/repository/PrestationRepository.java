package com.ouvriers.repository;

import com.ouvriers.models.Appointment;
import com.ouvriers.models.HistoriqueAppointment;
import com.ouvriers.models.Jeton;
import com.ouvriers.models.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long>  {

    @Query("select count(c) from Prestation c where c.ouvrier.id =:ouv")
    BigDecimal countNumberOfPrestationByOuvrierId(@Param("ouv") Long idOuv);

    List<Prestation> findPrestationByOrderByIdDesc();

    @Query("select p from Prestation p where p.ouvrier.id =:ouv order by id Desc")
    List<Prestation> FindPrestationsByOuvrierId(@Param("ouv") Long ouvId);

    @Query("select n from Prestation n where n.ouvrier.id =:num")
    List<Prestation> findTop4PrestationOrderByCreatedDateDesc(@Param("num") Long ouvRef);

    @Query("select n from Prestation n where n.ouvrier.id =:num")
    List<Prestation> findTop8PrestationOrderByCreatedDateDesc(@Param("num") Long ouvRef);
}
