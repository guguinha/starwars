package com.augusto.starwars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augusto.starwars.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
