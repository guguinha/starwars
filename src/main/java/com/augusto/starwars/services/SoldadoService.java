package com.augusto.starwars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.dto.SoldadoDTO;
import com.augusto.starwars.repositories.SoldadoRepository;
import com.augusto.starwars.services.exceptions.DataIntegrityException;
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
		soldado.setId(null); /* para garantir a inserção um novo soldado */
		return repo.save(soldado);
	}
	
	public Soldado update(Soldado obj) {
		Soldado newObj = find(obj.getId()); /* caso o id não exista o find já gera uma exceção, pois não se pode dar um update se o item não existe. */
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}
	}
	
	public List<Soldado> findAll(){
		return repo.findAll();
	}
	
	public Soldado fromDTO(SoldadoDTO objDTO) {
		return new Soldado(objDTO.getId(),objDTO.getNome(),objDTO.getIdade(), objDTO.getGenero(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getNomeBase(), null);
	}
	
	public void updateData(Soldado newObj, Soldado obj) {
		newObj.setNome(obj.getNome());
		newObj.setIdade(obj.getIdade());
		newObj.setGenero(obj.getGenero()); 
		newObj.setLatitude(obj.getLatitude()); 
		newObj.setLongitude(obj.getLongitude()); 
		newObj.setNomeBase(obj.getNomeBase());
	}
}
