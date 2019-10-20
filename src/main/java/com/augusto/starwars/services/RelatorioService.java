package com.augusto.starwars.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.starwars.domain.Iventario;
import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.repositories.SoldadoRepository;

@Service
public class RelatorioService {

	@Autowired	
	private SoldadoRepository repo;
	
	public Double traidores() {
		Double qtTraidor = 0.0;
		Double qtTotal = 0.0;
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			qtTotal += 1.0;
			if(list.get(i).getTipo().getCod()==2) {
				qtTraidor += 1.0;
			}
		}
		// % de traidores = qtTraido/qtTotal 
		return qtTraidor/qtTotal;
	}
	
	public Double rebeldes() {
		Double qtTraidor = 0.0;
		Double qtTotal = 0.0;
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			qtTotal += 1.0;
			if(list.get(i).getTipo().getCod()==2) {
				qtTraidor += 1.0;
			}
		}
		// % de traidores = qtTraido/qtTotal 
		return (qtTotal-qtTraidor)/qtTotal;	
	}

	public List<Double> mediaRecurso() {
		Integer qtRecurso1 = 0;
		Integer qtRecurso2 = 0;
		Integer qtRecurso3 = 0;
		Integer qtRecurso4 = 0;
		Double totalRebeldes = 0.0;
		List<Double> medias = new ArrayList<>();
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			totalRebeldes += 1.0;
			if(list.get(i).getTipo().getCod()==1) {
				Iterator<Iventario> iventarioIterator = list.get(i).getIventario().iterator();
				Integer j = 0;
				while (iventarioIterator.hasNext()){
					++j;
					Iventario iv = iventarioIterator.next();
					if(j==1) {
						qtRecurso1 += iv.getQuantidade();
					}
					if(j==2) {
						qtRecurso2 += iv.getQuantidade();
					}
					if(j==3) {
						qtRecurso3 += iv.getQuantidade();
					}
					if(j==4) {
						qtRecurso4 += iv.getQuantidade();
					}
		         }
			}
		}
		medias.addAll(Arrays.asList(qtRecurso1/totalRebeldes,qtRecurso2/totalRebeldes,qtRecurso3/totalRebeldes,qtRecurso4/totalRebeldes,totalRebeldes));
		return medias;
	}
	
	public Double pontosPerdidos() {
		Double lostPoints = 0.0;
		List<Soldado> list  = repo.findAll();
		for(int i=0;i<list.size();i++){ 
			if(list.get(i).getTipo().getCod()==2) {
				Iterator<Iventario> iventarioIterator = list.get(i).getIventario().iterator();
				while (iventarioIterator.hasNext()){
		                Iventario iv = iventarioIterator.next();
		                lostPoints += iv.getItem().getPontos() * iv.getQuantidade();
		         }
			}
		}
		return lostPoints;
	}
	
	
}
