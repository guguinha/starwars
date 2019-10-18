package com.augusto.starwars.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reporte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer id_rebelde;
	private Integer id_traidor;
	
	public Reporte() {
		
	}

	public Reporte(Integer id, Integer id_rebelde, Integer id_traidor) {
		super();
		this.id = id;
		this.id_rebelde = id_rebelde;
		this.id_traidor = id_traidor;
	}

	public Integer getId_rebelde() {
		return id_rebelde;
	}

	public void setId_rebelde(Integer id_rebelde) {
		this.id_rebelde = id_rebelde;
	}

	public Integer getId_traidor() {
		return id_traidor;
	}

	public void setId_traidor(Integer id_traidor) {
		this.id_traidor = id_traidor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reporte other = (Reporte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
