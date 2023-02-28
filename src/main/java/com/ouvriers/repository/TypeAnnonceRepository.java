package com.ouvriers.repository;

import com.ouvriers.models.TypeAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeAnnonceRepository extends JpaRepository<TypeAnnonce, Long> {

    List<TypeAnnonce> findTypeAnnonceByOrderByIdDesc();
}
