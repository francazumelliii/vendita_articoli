package it.jac.db.vendita.entity;

public class Magazzino extends BaseEntity {

	public String cod;
	public String indirizzo;


	public Magazzino(String cod, String indirizzo) {
		this.cod = cod;
		this.indirizzo = indirizzo;
	}

	public Magazzino() {

	}

	public String getCod() {
		return cod;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
}
