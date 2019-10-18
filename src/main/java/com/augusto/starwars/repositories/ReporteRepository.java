package com.augusto.starwars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augusto.starwars.domain.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

}

