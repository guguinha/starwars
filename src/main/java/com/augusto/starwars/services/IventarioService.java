package com.augusto.starwars.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Item;
import com.augusto.starwars.domain.IvItem;
import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.dto.ItemTrocaDTO;
import com.augusto.starwars.repositories.IventarioRepository;
import com.augusto.starwars.services.exceptions.Forbidden;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;

@Service
public class IventarioService {

	@Autowired	
	private IventarioRepository repo;
	
	@Autowired
	private ItemService itemService;
	
	public IvItem find(Integer id) {
		Optional<IvItem> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + IvItem.class.getName()));
	}
	
	public IvItem findByIvItem(IvItem i ) {
		Optional<IvItem> obj = repo.findByItemAndSoldado(i.getItem(),i.getSoldado());
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Soldado.id: " + i.getSoldado().getId() + ", Item.id: " + i.getItem().getId() +", Tipo: " + IvItem.class.getName()));
	}
	
	public void delete(Integer id) {
		throw new Forbidden("Não é possivel excluir iventario");
	}
	
	public IvItem update(IvItem obj) {
		throw new Forbidden("Não é possivel alterar iventario");
	}
	
	public IvItem fromDTO(ItemTrocaDTO objDTO) {
		return new IvItem(objDTO.getId(),objDTO.getQuantidade(),null, null);
	}
	
	public IvItem fromTradeDTO(ItemTrocaDTO objDTO,Soldado soldado) {
		Item item = itemService.find(objDTO.getId());
		IvItem novo = new IvItem(null, objDTO.getQuantidade(), item , soldado);
		return novo;
	}
	
}
