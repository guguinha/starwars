package com.augusto.starwars.services;

import java.util.List;
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
	
	public Item insert(Item item) {
		item.setId(null); /* para garantir a inserção um novo item */
		return repo.save(item);
	}
	
	public Item update(Item item) {
		find(item.getId()); /* caso o id não exista o find já gera uma exceção, pois não se pode dar um update se o item não existe. */
		return repo.save(item);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public List<Item> findAll(){
		return repo.findAll();
	}
	
}
