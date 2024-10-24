package it.jac.db.vendita.entity;

import java.time.LocalDateTime;

public class Vendita extends BaseEntity {

	private Articolo articolo;
	private PrezzoOfferta prezzoOfferta;
	private Magazzino magazzino;
	
	private LocalDateTime dataOra;
	
}
