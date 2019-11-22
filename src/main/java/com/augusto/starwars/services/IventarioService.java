package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.IvItem;
import com.augusto.starwars.dto.IventarioDTO;
import com.augusto.starwars.repositories.IventarioRepository;
import com.augusto.starwars.services.exceptions.Forbidden;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;

@Service
public class IventarioService {

	@Autowired	
	private IventarioRepository repo;
	
	public IvItem find(Integer id) {
		Optional<IvItem> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + IvItem.class.getName()));
	}
	
	public void delete(Integer id) {
		throw new Forbidden("Não é possivel excluir iventario");
	}
	
	public IvItem update(IvItem obj) {
		throw new Forbidden("Não é possivel alterar iventario");
	}
	
	public IvItem fromDTO(IventarioDTO objDTO) {
		return new IvItem(objDTO.getId(),objDTO.getQuantidade(),null, null);
	}
	
}
