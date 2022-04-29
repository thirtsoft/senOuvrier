package com.ouvriers.repository;

import com.ouvriers.models.Jeton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JetonRepository extends JpaRepository<Jeton, Long> {

    List<Jeton> findListOfJetonByOrderByIdDesc();

    @Query("select p from Jeton p where p.utilisateur.id =:user order by id Desc")
    List<Jeton> FindListJetonByCustomerId(@Param("user") Long userId);

}
