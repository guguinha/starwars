package com.augusto.starwars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augusto.starwars.domain.IvItem;

@Repository
public interface IventarioRepository extends JpaRepository<IvItem, Integer>{

}
