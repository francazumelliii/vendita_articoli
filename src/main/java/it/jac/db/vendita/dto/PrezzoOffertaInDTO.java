package it.jac.db.vendita.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class PrezzoOffertaInDTO {

	private long idArticolo;
	private double valore;
	@NotNull
	private LocalDate dataInizio;
	@NotNull
	private LocalDate dataFine;

	public long getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(long idArticolo) {
		this.idArticolo = idArticolo;
	}

	public double getValore() {
		return valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

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

	@Override
	public String toString() {
		return "PrezzoOffertaInDTO [idArticolo=" + idArticolo + ", valore=" + valore + ", dataInizio=" + dataInizio
				+ ", dataFine=" + dataFine + "]";
	}

}
