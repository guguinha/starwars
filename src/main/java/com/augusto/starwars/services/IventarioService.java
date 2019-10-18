package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Iventario;
import com.augusto.starwars.repositories.IventarioRepository;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;

@Service
public class IventarioService {

	@Autowired	
	private IventarioRepository repo;
	
	public Iventario find(Integer id) {
		Optional<Iventario> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Iventario.class.getName()));
	}
}
