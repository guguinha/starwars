package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.repositories.SoldadoRepository;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;

@Service
public class SoldadoService {
	
	@Autowired	
	private SoldadoRepository repo;
	
	public Soldado find(Integer id) {
		Optional<Soldado> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Soldado.class.getName()));
	}
	
	public Soldado insert(Soldado soldado) {
		soldado.setId(null); /* para garantir a inserção um novo item */
		return repo.save(soldado);
	}
}
