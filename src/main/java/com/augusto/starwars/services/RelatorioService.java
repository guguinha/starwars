package com.augusto.starwars.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.IvItem;
import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.repositories.SoldadoRepository;

@Service
public class RelatorioService {
	
	/* 
	 * Numa API o ideal seria retornar o números e as mensagens ficariam a cargo do frontend, 
	 * mas para testes achei melhor organizas as mensagens via API
	 */
	@Autowired	
	private SoldadoRepository repo;
	
	public String traidores() {
		Double qtTraidor = 0.0;
		Double qtTotal = 0.0;
		Double traidores;
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			qtTotal += 1.0;
			if(list.get(i).getTipo().getCod()==2) {
				qtTraidor += 1.0;
			}
		}
		// % de traidores = qtTraidor/qtTotal 
		traidores = qtTraidor/qtTotal;
		String msg = traidores+"%, de um total de "+ qtTotal+" soldados, são rebeldes";
		return msg;
	}
	
	public String rebeldes() {
		Double qtTraidor = 0.0;
		Double qtTotal = 0.0;
		Double rebeldes;
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			qtTotal += 1.0;
			if(list.get(i).getTipo().getCod()==2) {
				qtTraidor += 1.0;
			}
		}
		// % de traidores = qtTraido/qtTotal 
		rebeldes = (qtTotal-qtTraidor)/qtTotal;
		String msg = rebeldes+"%, de um total de "+ qtTotal+" soldados, são rebeldes";
		return msg;	
	}

	public List<String> mediaRecurso() {
		Integer qtRecurso1 = 0;
		Integer qtRecurso2 = 0;
		Integer qtRecurso3 = 0;
		Integer qtRecurso4 = 0;
		Double totalRebeldes = 0.0;
		List<String> medias = new ArrayList<>();
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			//Excluir Traidores
			if(list.get(i).getTipo().getCod()==1) {
				totalRebeldes += 1.0;
				Iterator<IvItem> iventarioIterator = list.get(i).getIventario().iterator();
				while (iventarioIterator.hasNext()){
					IvItem iv = iventarioIterator.next();
					if(iv.getItem().getId() == 1) {
						qtRecurso1 += iv.getQuantidade();
					}
					if(iv.getItem().getId()==2) {
						qtRecurso2 += iv.getQuantidade();
					}
					if(iv.getItem().getId()==3) {
						qtRecurso3 += iv.getQuantidade();
					}
					if(iv.getItem().getId()==4) {
						qtRecurso4 += iv.getQuantidade();
					}
		         }
			}
		}
		medias.addAll(Arrays.asList(
				"Num total de "+totalRebeldes+" rebeldes a media de itens é:",
				qtRecurso1/totalRebeldes+" armas por rebelde",
				qtRecurso2/totalRebeldes+" munições por rebelde",
				qtRecurso3/totalRebeldes+" águas por rebelde",
				qtRecurso4/totalRebeldes+" comida por rebelde"
				));
		return medias;
	}
	
	public String pontosPerdidos() {
		Double lostPoints = 0.0;
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			if(list.get(i).getTipo().getCod()==2) {
				Iterator<IvItem> iventarioIterator = list.get(i).getIventario().iterator();
				while (iventarioIterator.hasNext()){
		                IvItem iv = iventarioIterator.next();
		                lostPoints += iv.getItem().getPontos() * iv.getQuantidade();
		         }
			}
		}
		return "Pontos perdidos devido a traidores: " + lostPoints + " pontos";
	}
	
	
	
}
