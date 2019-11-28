package com.augusto.starwars.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augusto.starwars.domain.Item;
import com.augusto.starwars.domain.IvItem;
import com.augusto.starwars.domain.Soldado;

@Repository
public interface IventarioRepository extends JpaRepository<IvItem, Integer>{
	
	Optional<IvItem> findByItemAndSoldado(Item i,Soldado s);
	
}
