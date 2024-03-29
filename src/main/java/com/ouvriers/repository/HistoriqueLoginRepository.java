package com.ouvriers.repository;

import com.ouvriers.models.HistoriqueLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueLoginRepository extends JpaRepository<HistoriqueLogin, Long> {

    List<HistoriqueLogin> findHistoriqueLoginByOrderByIdDesc();

    List<HistoriqueLogin> findTop30ByOrderByIdDesc();

}
