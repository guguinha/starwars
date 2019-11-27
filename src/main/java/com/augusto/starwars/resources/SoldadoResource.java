package com.augusto.starwars.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.dto.LocalizacaoDTO;
import com.augusto.starwars.dto.NovoSoldadoDTO;
import com.augusto.starwars.dto.SoldadoDTO;
import com.augusto.starwars.dto.tradeDTO;
import com.augusto.starwars.services.SoldadoService;

@RestController
@RequestMapping(value="/soldados")
public class SoldadoResource {
	
	
	@Autowired
	private SoldadoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Soldado> find(@PathVariable Integer id) {
		Soldado obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody NovoSoldadoDTO objDTO){
		Soldado obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody SoldadoDTO objDTO, @PathVariable Integer id){
		Soldado obj = service.fromDTO(objDTO);
		obj.setId(id); // para garantir que o update seja realizado no Soldado correto
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SoldadoDTO>> findAll() {
		List<Soldado> list = service.findAll();
		List<SoldadoDTO> listDTO = list.stream().map(obj -> new SoldadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/localizacao/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<Void> updateLocal(@RequestBody LocalizacaoDTO objDTO, @PathVariable Integer id){
		Soldado obj = service.fromLocalDTO(objDTO);
		obj.setId(id); // para garantir que o update seja realizado no Soldado correto
		obj = service.updateLocal(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/trade")
	public ResponseEntity<Void> trade(@RequestBody tradeDTO objDTO){
		service.trade(objDTO);
		return ResponseEntity.noContent().build();
	}
	
}
