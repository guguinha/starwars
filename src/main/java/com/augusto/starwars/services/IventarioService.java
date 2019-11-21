package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Iventario;
import com.augusto.starwars.dto.IventarioDTO;
import com.augusto.starwars.repositories.IventarioRepository;
import com.augusto.starwars.services.exceptions.Forbidden;
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
	
	public void delete(Integer id) {
		throw new Forbidden("Não é possivel excluir iventario");
	}
	
	public Iventario update(Iventario obj) {
		throw new Forbidden("Não é possivel alterar iventario");
	}
	
	public Iventario fromDTO(IventarioDTO objDTO) {
		return new Iventario(objDTO.getId(),objDTO.getQuantidade(),null, null);
	}
	
}
