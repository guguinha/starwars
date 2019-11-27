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
	private IventarioRepository iventarioRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private IventarioService iventarioService;
	
	@Autowired
	private ItemService itemService;
	
	public Soldado find(Integer id) {
		Optional<Soldado> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Soldado.class.getName()));
	}
	
	public Soldado insert(Soldado soldado) {
		soldado.setId(null); /* para garantir a inserção um novo soldado */
		soldado = repo.save(soldado);
		iventarioRepository.saveAll(soldado.getIventario());
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
	
	//otimizar função 
	//verificar se algum soldado é traidor, não é permitido troca com traidores
	@Transactional
	public void trade(tradeDTO objDTO) {
		int i; // contador
		Item item;
		List<ItemTrocaDTO> itensTrade1 = objDTO.getItens1();
		List<ItemTrocaDTO> itensTrade2 = objDTO.getItens2();
		//seta o item da trade o item correspondente apartir do id recebido
		for(i = 0;i<itensTrade1.size();i++) {
			item = itemService.find(itensTrade1.get(i).getId());
			itensTrade1.get(i).setPontos(item.getPontos());
		}
		//seta o item da trade o item correspondente apartir do id recebido
		for(i = 0;i<itensTrade2.size();i++) {
			item = itemService.find(itensTrade2.get(i).getId());
			itensTrade2.get(i).setPontos(item.getPontos());
		}
		Soldado soldado1 = find(objDTO.getId_soldado_1()); // Se for nulo já gera exceção no find
		Soldado soldado2 = find(objDTO.getId_soldado_2()); // Se for nulo já gera exceção no find
		Integer valorItens1 = valueItens(itensTrade1);
		Integer valorItens2 = valueItens(itensTrade2);
		if(valorItens1 == valorItens2) {
			if(!containItens(soldado1.getIventario(), itensTrade1)) {
				//lança exceção itens da troca não batem com o iventario1
				return;
			}
			if(!containItens(soldado2.getIventario(), itensTrade2)) {
				//lança exceção itens da troca não batem com o iventario2
				return;
			}
			//realizar troca
			
			//remover itens dos iventarios dos dois soldados
			for(i = 0;i<itensTrade1.size();i++) {				
				//recebe um IvItem, itensTrade tem que virar um IvItem
				itemIncDec(iventarioService.fromTradeDTO(itensTrade1.get(i),soldado1), "DEC");
				itemIncDec(iventarioService.fromTradeDTO(itensTrade1.get(i),soldado2), "INC");
			}
			for(i = 0;i<itensTrade2.size();i++) {
				itemIncDec(iventarioService.fromTradeDTO(itensTrade2.get(i),soldado2), "DEC");
				itemIncDec(iventarioService.fromTradeDTO(itensTrade2.get(i),soldado1), "INC");
			}	
			//finalizar troca 
		}else {
			//valor da troca não bate
		}
	}
	
	// Incrememnta ou decrementa item
		public void itemIncDec(IvItem ivItem, String opcao) {
			Soldado soldado = ivItem.getSoldado();
			Iterator<IvItem> ivIterator = soldado.getIventario().iterator();
			// antes é feito o teste se o soldado tem os itens para troca, em caso de decremento aqui é certo que vai achar o item
			boolean itemNovo = true;
			while (ivIterator.hasNext()){
				IvItem iv = ivIterator.next();
				if(iv.getItem().getId() == ivItem.getItem().getId() ) {
					itemNovo = false;
					//incrementa 
					if(opcao.equals("INC")) {
						iv.setQuantidade(iv.getQuantidade() + ivItem.getQuantidade());
						iventarioRepository.save(iv);
						break;
					}else {
					//ou decrementa
						if(opcao.equals("DEC")) {
							if(iv.getQuantidade() == ivItem.getQuantidade()){
								// remove
								//ivIterator.remove();
								//IvItem ivItem2 = iventarioService.findByIvItem(ivItem);
								//ivItem.setId(iventarioService.findByIvItem(ivItem).getId());
								iventarioRepository.delete(iv);
								iv.delete();
								/*iv.getSoldado().getIventario().remove(ivItem2);
								//limpar referencia dos iventarios removidos nos itens e no soldado
										
										ivItem2.setSoldado(null);
										ivItem2.getItem().getIvItem().remove(ivItem2);
										ivItem2.setItem(null);
								*/
																
								//no banco ao remover um iventario as referencias para soldado e item são removidas, mas no programa continua 
												
								//verificar necessidade de salvar item também
				
								break;
							}else 
								if(iv.getQuantidade() > ivItem.getQuantidade()) {
									iv.setQuantidade(iv.getQuantidade() - ivItem.getQuantidade());
									iventarioRepository.save(iv);
									break;
								}else {
									// erro na quantidade a ser removida
									break;
								}
								
						}else {
							//error na opção escolhida
							break;
						}
					}
				}
			}
			if(itemNovo) {
				// item não esta disponivel no iventario
				if(opcao.equals("INC")) {
					//adiciona novo item no iventario
					IvItem ivItem2 = new IvItem(ivItem);
					soldado.getIventario().add(ivItem2);
					iventarioRepository.save(ivItem2);
					//soldado.getIventario().add(ivItem2);
					//repo.save(soldado);	
				}else {
					//não da para remover um item ausente
				}
			}
			
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
				//item inesistente ou quantidade insuficiente..
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
