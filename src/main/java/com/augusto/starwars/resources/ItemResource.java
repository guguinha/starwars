package com.augusto.starwars.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.starwars.domain.Item;
import com.augusto.starwars.services.ItemService;

@RestController
@RequestMapping(value="/itens")
public class ItemResource {
	
	@Autowired
	private ItemService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Item obj = service.find(id);
	
		return ResponseEntity.ok().body(obj);
	}
}
