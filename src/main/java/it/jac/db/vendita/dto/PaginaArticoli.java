package it.jac.db.vendita.dto;

import it.jac.db.vendita.entity.Articolo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginaArticoli {

	private List<ArticoloOutDTO> list = new ArrayList<>();
	private int totaleRecord;
	private Map<String, Object> filters = new HashMap<>();
	private int pagina;
	private int dimPagina;
	private String ordina;
	
	public void addAll(List<ArticoloOutDTO> list) {
		
		this.list.addAll(list);
	}
	
	public List<ArticoloOutDTO> getList() {

		return Collections.unmodifiableList(this.list);
	}
	
	public void addFilter(String name, Object value) {
		
		this.filters.put(name, value);
	}
	
	public Map<String, Object> getFilters() {
		
		return this.filters;
	}

	public int getTotaleRecord() {
		return totaleRecord;
	}

	public void setTotaleRecord(int totaleRecord) {
		this.totaleRecord = totaleRecord;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getDimPagina() {
		return dimPagina;
	}

	public void setDimPagina(int dimPagina) {
		this.dimPagina = dimPagina;
	}

	public String getOrdina() {
		return ordina;
	}

	public void setOrdina(String ordina) {
		this.ordina = ordina;
	}


	
}
