package com.ouvriers.repository;

import com.ouvriers.models.WhistList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WhistListRepository extends JpaRepository<WhistList, Long> {

    List<WhistList> findTop3ByOrderByCreatedDateDesc();

    @Query("select count(c) from WhistList c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfWhistList();

    List<WhistList> findByOrderByIdDesc();

    @Query("select count(c) from WhistList c where c.ouvrier.reference =:ouv")
    BigDecimal countNumberOfWhistListByOuvrierId(@Param("ouv") String OuvRef);

    @Query("select n from WhistList n where n.ouvrier.reference =:num")
    List<WhistList> findTop4WhistListOrderByCreatedDateDesc(@Param("num") String OuvRef);

}
