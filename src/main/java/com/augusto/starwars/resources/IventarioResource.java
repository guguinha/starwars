package com.augusto.starwars.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.starwars.domain.Iventario;
import com.augusto.starwars.services.IventarioService;

@RestController
@RequestMapping(value="/iventario")
public class IventarioResource {

	@Autowired
	private IventarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Iventario obj = service.find(id);
	
		return ResponseEntity.ok().body(obj);
	}
}
