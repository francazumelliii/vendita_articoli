package it.jac.db.vendita.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.QueryParam;

import java.time.LocalDate;

public class ArticoloOffertaFilterDTO {

	@QueryParam("data_inizio")
	@NotNull
	private LocalDate dataInizio;
	@QueryParam("data_fine")
	@NotNull
	private LocalDate dataFine;
	@QueryParam("pagina")
	@Min(value = 1)
	private int pagina;
	@QueryParam("dim_pagina")
	@Min(value = 10)
	@Max(value = 50)
	private int dimPagina;
	@QueryParam("ordina")
	private String ordina;

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
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
