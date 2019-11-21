package com.augusto.starwars.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Item;
import com.augusto.starwars.domain.Iventario;
import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.domain.enums.TipoSoldado;
import com.augusto.starwars.dto.LocalizacaoDTO;
import com.augusto.starwars.dto.NovoSoldadoDTO;
import com.augusto.starwars.dto.SoldadoDTO;
import com.augusto.starwars.dto.tradeDTO;
import com.augusto.starwars.repositories.ItemRepository;
import com.augusto.starwars.repositories.IventarioRepository;
import com.augusto.starwars.repositories.SoldadoRepository;
import com.augusto.starwars.services.exceptions.DataIntegrityException;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;

@Service
public class SoldadoService {
	
	@Autowired	
	private SoldadoRepository repo;
	
	@Autowired
	private IventarioRepository iventariorepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Soldado find(Integer id) {
		Optional<Soldado> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Soldado.class.getName()));
	}
	
	public Soldado insert(Soldado soldado) {
		soldado.setId(null); /* para garantir a inserção um novo soldado */
		soldado = repo.save(soldado);
		iventariorepository.saveAll(soldado.getIventario());
		return soldado;
	}
	
	public Soldado update(Soldado obj) {
		Soldado newObj = find(obj.getId()); /* caso o id não exista o find já gera uma exceção, pois não se pode dar um update se o soldado não existe. */
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public Soldado updateLocal(Soldado obj) {
		Soldado newObj = find(obj.getId()); /* caso o id não exista o find já gera uma exceção, pois não se pode dar um update se o soldado não existe. */
		updateDataLocal(newObj, obj);
		return repo.save(newObj);
	}
	
	public Soldado updateTipo(Soldado obj) {
		Soldado newObj = find(obj.getId()); /* caso o id não exista o find já gera uma exceção, pois não se pode dar um update se o soldado não existe. */
		updateDataTipo(newObj, obj);
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
	
	public void trade(tradeDTO objDTO) {
		
	}
	
	public Soldado fromDTO(SoldadoDTO objDTO) {
		return new Soldado(objDTO.getId(),objDTO.getNome(),objDTO.getIdade(), objDTO.getGenero(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getNomeBase(), null);
	}
	
	public Soldado fromDTO(NovoSoldadoDTO objDTO) {
		Soldado rebelde = new Soldado(null, objDTO.getNome(),objDTO.getIdade(), objDTO.getGenero(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getNomeBase(), TipoSoldado.toEnum(1));
		List<Iventario> ListIv = new ArrayList<>();
		List<Item> listItem = itemRepository.findAll();
		if(objDTO.getQuantidadeItem1()>0) {
			Iventario iv1 = new Iventario(null, objDTO.getQuantidadeItem1(), listItem.get(1), rebelde);
			ListIv.add(iv1);
			//rebelde.getIventario().add(iv1);
		}
		if(objDTO.getQuantidadeItem2()>0) {
			Iventario iv2 = new Iventario(null, objDTO.getQuantidadeItem2(), listItem.get(2), rebelde);
			ListIv.add(iv2);
			//rebelde.getIventario().add(iv2);
		}
		if(objDTO.getQuantidadeItem3()>0) {
			Iventario iv3 = new Iventario(null, objDTO.getQuantidadeItem3(), listItem.get(3), rebelde);
			ListIv.add(iv3);
			//rebelde.getIventario().add(iv3);
		}
		if(objDTO.getQuantidadeItem4()>0) {
			Iventario iv4 = new Iventario(null, objDTO.getQuantidadeItem4(), listItem.get(4), rebelde);
			ListIv.add(iv4);
		}
		rebelde.getIventario().addAll(ListIv);
		return rebelde;
	}
	
	public Soldado fromLocalDTO(LocalizacaoDTO objDTO) {
		return new Soldado(objDTO.getId(), null, null, null, objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getNomeBase(), null);
	}
	
	public Soldado toTraidor(Soldado obj) {
		return new Soldado(obj.getId(), null, null, null, null, null, null, TipoSoldado.toEnum(2));
	}
	
	public void updateData(Soldado newObj, Soldado obj) {
		newObj.setNome(obj.getNome());
		newObj.setIdade(obj.getIdade());
		newObj.setGenero(obj.getGenero()); 
		newObj.setLatitude(obj.getLatitude()); 
		newObj.setLongitude(obj.getLongitude()); 
		newObj.setNomeBase(obj.getNomeBase());
	}
	
	public void updateDataLocal(Soldado newObj, Soldado obj) {
		newObj.setLatitude(obj.getLatitude()); 
		newObj.setLongitude(obj.getLongitude()); 
		newObj.setNomeBase(obj.getNomeBase());
	}
	
	public void updateDataTipo(Soldado newObj, Soldado obj) {
		newObj.setTipo(obj.getTipo()); 
	}
}
