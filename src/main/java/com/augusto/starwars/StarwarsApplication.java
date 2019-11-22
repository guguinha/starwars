package com.augusto.starwars;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.augusto.starwars.domain.Item;
import com.augusto.starwars.domain.IvItem;
import com.augusto.starwars.domain.Reporte;
import com.augusto.starwars.domain.Soldado;
import com.augusto.starwars.domain.enums.TipoSoldado;
import com.augusto.starwars.repositories.ItemRepository;
import com.augusto.starwars.repositories.IventarioRepository;
import com.augusto.starwars.repositories.ReporteRepository;
import com.augusto.starwars.repositories.SoldadoRepository;

@SpringBootApplication
public class StarwarsApplication implements CommandLineRunner{

	//repositorio para testes
	@Autowired
	private SoldadoRepository soldadoRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private IventarioRepository iventarioRepository;
	@Autowired
	private ReporteRepository reporteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(StarwarsApplication.class, args);
	}

	//instanciamento automatico do banco de dados para testes 
	@Override
	public void run(String... args) throws Exception {
		
		Item arma = new Item(null, "Arma",4);
		Item municao = new Item(null, "Munição",3);
		Item agua = new Item(null, "Água",2);
		Item comida = new Item(null, "Comida",1);
		
		Soldado reb1 = new Soldado(null, "Jefferson",32,"M",2.3754,-2.59872,"baseX75",TipoSoldado.REBELDE);
		Soldado reb2 = new Soldado(null, "Jane",28,"F",2.3754,-2.59872,"baseX75",TipoSoldado.REBELDE);
		Soldado reb3 = new Soldado(null, "Gigsaw",189,"M",1.2789,-17.2322,"Gilad",TipoSoldado.REBELDE);
		Soldado reb4 = new Soldado(null, "chronus",45,"M",2.3754,-2.59872,"baseX75",TipoSoldado.TRAIDOR);
			
		IvItem iv1 = new IvItem(null, 1, arma, reb1);
		IvItem iv2 = new IvItem(null, 2, municao, reb1);
		IvItem iv3 = new IvItem(null, 1, arma, reb2);
		IvItem iv4 = new IvItem(null, 3, agua, reb2);
		IvItem iv5 = new IvItem(null, 4, comida, reb2);
		IvItem iv6 = new IvItem(null, 5, comida, reb3);
		IvItem iv7 = new IvItem(null, 7, agua, reb3);
		IvItem iv8 = new IvItem(null, 2, arma, reb4);
		IvItem iv9 = new IvItem(null, 8, municao, reb4);
		IvItem iv10 = new IvItem(null, 1, comida, reb4);
		IvItem iv11 = new IvItem(null, 1, agua, reb4);
		
		reb1.getIventario().addAll(Arrays.asList(iv1,iv2));
		reb2.getIventario().addAll(Arrays.asList(iv3,iv4,iv5));
		reb3.getIventario().addAll(Arrays.asList(iv6,iv7));
		reb4.getIventario().addAll(Arrays.asList(iv8,iv9,iv10,iv11));	
		
		
		soldadoRepository.saveAll(Arrays.asList(reb1,reb2,reb3,reb4));
		itemRepository.saveAll(Arrays.asList(arma,municao,agua,comida));
		iventarioRepository.saveAll(Arrays.asList(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11));
		
		Reporte rep = new Reporte(null,reb1.getId(),reb4.getId());
		reporteRepository.saveAll(Arrays.asList(rep));
		
	}

}
