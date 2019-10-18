package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Reporte;
import com.augusto.starwars.repositories.ReporteRepository;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;

@Service
public class ReporteService {

	@Autowired	
	private ReporteRepository repo;
	
	public Reporte find(Integer id) {
		Optional<Reporte> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Reporte.class.getName()));
	}
}
