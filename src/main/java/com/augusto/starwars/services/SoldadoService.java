package com.augusto.starwars.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Item;
import com.augusto.starwars.domain.IvItem;
import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.domain.enums.TipoSoldado;
import com.augusto.starwars.dto.ItemTrocaDTO;
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
	
	@Autowired
	private IventarioService iventarioService;
	
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
	
	/*
	public Soldado updateTipo(Soldado obj) {
		Soldado newObj = find(obj.getId()); /* caso o id não exista o find já gera uma exceção, pois não se pode dar um update se o soldado não existe. *
		updateDataTipo(newObj, obj);
		return repo.save(newObj);
	}
	*/
	
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
	
	@Transactional
	public void trade(tradeDTO objDTO) {
		List<ItemTrocaDTO> itensTrade1 = objDTO.getItens1();
		List<ItemTrocaDTO> itensTrade2 = objDTO.getItens2();
		Soldado soldado1 = find(objDTO.getId_soldado_1()); // Se for nulo já gera exceção no find
		Soldado soldado2 = find(objDTO.getId_soldado_2()); // Se for nulo já gera exceção no find
		Integer valorItens1 = valueItens(itensTrade1);
		Integer valorItens2 = valueItens(itensTrade2);
		if(valorItens1 == valorItens2) {
			if(!containItens(soldado1.getIventario(), itensTrade1)) {
				//lança exceção intens da troca não batem com o iventario
			}
			if(!containItens(soldado2.getIventario(), itensTrade2)) {
				//lança exceção intens da troca não batem com o iventario
			}
			//realizar troca
			
			//remover itens dos iventarios dos dois soldados
			for(int i = 0;i<=itensTrade1.size();i++) {
				//recebe um IvItem, itensTrade tem que virar um IvItem
				
				soldado1.itemIncDec(iventarioService.fromTradeDTO(itensTrade1.get(i)), "DEC");
			}
			for(int i = 0;i<=itensTrade1.size();i++) {
				soldado1.itemIncDec(iventarioService.fromTradeDTO(itensTrade1.get(i)), "DEC");
			}
			//adicionar itens no iventario
			for(int i = 0;i<=itensTrade2.size();i++) {
				soldado1.itemIncDec(iventarioService.fromTradeDTO(itensTrade2.get(i)), "INC");
			}
			for(int i = 0;i<=itensTrade2.size();i++) {
				soldado1.itemIncDec(iventarioService.fromTradeDTO(itensTrade2.get(i)), "INC");
			}
			//finalizar troca 
		}
		//otimizar função 
	}
	
	//Retorna o total de pontos de uma lista de itens de troca
	public Integer valueItens(List<ItemTrocaDTO> itens) {
		Integer totalPoints = 0;
		for(int i=0;i<itens.size();i++){ 
			totalPoints += itens.get(i).getPontos();
		}
		return totalPoints;
	}
	
	// Verifica se o item da troca esta contido e se tem o suficiente no iventario do soldado
	public boolean containItens(Set<IvItem> obj, List<ItemTrocaDTO> itensTrade) {
		boolean estaContido;
		for(int i=0;i<itensTrade.size();i++){ 
			estaContido = false;//item trade esta contido e na quantidade certa no iventario do soldado ?
			Iterator<IvItem> iventarioIterator = obj.iterator();
			while (iventarioIterator.hasNext()){
				IvItem iv = iventarioIterator.next();
				if(iv.getItem().getId() == itensTrade.get(i).getId() && iv.getQuantidade() >= itensTrade.get(i).getQuantidade()) {
					estaContido = true;
					break;
				}
	         }
			if(!estaContido) {
				return false; // algum item da trade não bate com o iventario do soldado
			}
		}
		return true;
	}

	
	public Soldado fromDTO(SoldadoDTO objDTO) {
		return new Soldado(objDTO.getId(),objDTO.getNome(),objDTO.getIdade(), objDTO.getGenero(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getNomeBase(), null);
	}
	
	public Soldado fromDTO(NovoSoldadoDTO objDTO) {
		Soldado rebelde = new Soldado(null, objDTO.getNome(),objDTO.getIdade(), objDTO.getGenero(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getNomeBase(), TipoSoldado.toEnum(1));
		List<IvItem> ListIv = new ArrayList<>();
		List<Item> listItem = itemRepository.findAll();
		if(objDTO.getQuantidadeItem1()>0) {
			IvItem iv1 = new IvItem(null, objDTO.getQuantidadeItem1(), listItem.get(0), rebelde);
			ListIv.add(iv1);
		}
		if(objDTO.getQuantidadeItem2()>0) {
			IvItem iv2 = new IvItem(null, objDTO.getQuantidadeItem2(), listItem.get(1), rebelde);
			ListIv.add(iv2);
		}
		if(objDTO.getQuantidadeItem3()>0) {
			IvItem iv3 = new IvItem(null, objDTO.getQuantidadeItem3(), listItem.get(2), rebelde);
			ListIv.add(iv3);
		}
		if(objDTO.getQuantidadeItem4()>0) {
			IvItem iv4 = new IvItem(null, objDTO.getQuantidadeItem4(), listItem.get(3), rebelde);
			ListIv.add(iv4);
		}
		rebelde.getIventario().addAll(ListIv);
		return rebelde;
	}
	
	public Soldado fromLocalDTO(LocalizacaoDTO objDTO) {
		return new Soldado(objDTO.getId(), null, null, null, objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getNomeBase(), null);
	}
	/*
	public Soldado toTraidor(Soldado obj) {
		return new Soldado(obj.getId(), null, null, null, null, null, null, TipoSoldado.toEnum(2));
	}
	*/
	
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
	
	/*
	public void updateDataTipo(Soldado newObj, Soldado obj) {
		newObj.setTipo(obj.getTipo()); 
	}
	*/
}
