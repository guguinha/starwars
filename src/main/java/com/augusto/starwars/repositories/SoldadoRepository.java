package com.augusto.starwars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augusto.starwars.domain.Soldado;

@Repository
public interface SoldadoRepository extends JpaRepository<Soldado, Integer> {

}
