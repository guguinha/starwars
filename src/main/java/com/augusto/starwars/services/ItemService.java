package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Item;
import com.augusto.starwars.repositories.ItemRepository;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;

@Service
public class ItemService {

	@Autowired	
	private ItemRepository repo;
	
	public Item find(Integer id) {
		Optional<Item> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Item.class.getName()));
	}
	
}
