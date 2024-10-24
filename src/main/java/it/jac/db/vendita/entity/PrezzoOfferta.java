package it.jac.db.vendita.entity;

import java.time.LocalDate;

public class PrezzoOfferta extends BaseEntity {

	public Articolo articolo;
	public double valore;
	public LocalDate dataInizio;
	public LocalDate dataFine;
	
}
