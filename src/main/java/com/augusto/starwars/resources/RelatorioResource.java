package com.augusto.starwars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.starwars.services.RelatorioService;

@RestController
@RequestMapping(value="/relatorios")
public class RelatorioResource {
	
	@Autowired
	private RelatorioService service;

	@RequestMapping(value="/traidores", method=RequestMethod.GET)
	public ResponseEntity<String> traidores() {
		String traidores = service.traidores();
		return ResponseEntity.ok().body(traidores);
	}
	
	@RequestMapping(value="/rebeldes", method=RequestMethod.GET)
	public ResponseEntity<String> rebeldes() {
		String rebeldes = service.rebeldes();
		return ResponseEntity.ok().body(rebeldes);
	}
	
	@RequestMapping(value="/recursosmedia", method=RequestMethod.GET)
	public ResponseEntity<List<String>> mediaRecurso() {
		List<String> recursos = service.mediaRecurso();
		return ResponseEntity.ok().body(recursos);
	}
	
	@RequestMapping(value="/perdas", method=RequestMethod.GET)
	public ResponseEntity<String> pontosPerdidos() {
		String pontosPerdidos = service.pontosPerdidos();
		return ResponseEntity.ok().body(pontosPerdidos);
	}
}
