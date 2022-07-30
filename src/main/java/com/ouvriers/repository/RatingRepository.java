package com.ouvriers.repository;

import com.ouvriers.models.Jeton;
import com.ouvriers.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findTop3ByOrderByCreatedDateDesc();

    @Query("select count(c) from Rating c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfRating();

    List<Rating> findByOrderByIdDesc();

    @Query("select count(c) from Rating c where c.ouvrier.id =:ouv")
    BigDecimal countNumberOfRatingByOuvrierId(@Param("ouv") Long idOuv);

    @Query("select n from Rating n where n.ouvrier.id =:num")
    List<Rating> findTop4RatingOrderByCreatedDateDesc(@Param("num") Long ouvRef);

    @Query("select p from Rating p where p.utilisateur.id =:user order by id Desc")
    List<Rating> FindListRatingByCustomerId(@Param("user") Long userId);
}
