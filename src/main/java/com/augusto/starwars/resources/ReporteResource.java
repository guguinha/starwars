package com.augusto.starwars.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.starwars.domain.Reporte;
import com.augusto.starwars.services.ReporteService;

@RestController
@RequestMapping(value="/reportes")
public class ReporteResource {

	@Autowired
	private ReporteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Reporte obj = service.find(id);
	
		return ResponseEntity.ok().body(obj);
	}
}

