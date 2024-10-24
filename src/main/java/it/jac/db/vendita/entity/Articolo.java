package it.jac.db.vendita.entity;

import java.util.ArrayList;
import java.util.List;

public class Articolo extends BaseEntity {

//	active record pattern
//	le entità sono oggetti che operano direttamente su se' stessi (es. entity.save())
	
	public String marca;
	public String modello;
	public String descrizione;
	public double prezzoBase;
	
	public List<PrezzoOfferta> offerte = new ArrayList<>();
	public List<Stock> disponibilita = new ArrayList<>();
	
//	non è una vera proprietà della tabella
//	viene calcolata quando mi serve ottenere il conteggio totale di una query limitata
	public int totaleRecord;
}
