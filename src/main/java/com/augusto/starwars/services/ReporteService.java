package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Reporte;
import com.augusto.starwars.domain.Soldado;
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
	
	public Reporte insert(Reporte obj) {
		//Integer idTraidor = obj.getId_traidor();
		obj.setId(null); /* para garantir a inserção um novo soldado */
		//verifica se reporte é duplicado idRebelde/idTraidor p/ n repetir
		repo.save(obj);
		//verifica se o rebelde denunciado já tem tres denuncias
		
		//se sim
		/*{
			Soldado novo = new Soldado();
			SoldadoService ss new SoldadoService();
			novo.setId(idTraidor);
			novo = ss.toTraidor(novo);
			ss.updateTipo(novo);
		}*/
		
		return repo.save(obj);
	}
	
}
