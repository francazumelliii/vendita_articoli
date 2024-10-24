package it.jac.db.vendita.dto;

import java.time.LocalDate;

import it.jac.db.vendita.entity.PrezzoOfferta;

public class PrezzoOffertaOutDTO {

	public long id;
	public double valore;
	public LocalDate dataInizio;
	public LocalDate dataFine;
	public boolean active;
	
	private PrezzoOffertaOutDTO() {
		
	}
	
//	factory method
	public static PrezzoOffertaOutDTO build(PrezzoOfferta entity) {
		
		PrezzoOffertaOutDTO dto = new PrezzoOffertaOutDTO();
		dto.id = entity.id;
		dto.valore = entity.valore;
		dto.dataInizio = entity.dataInizio;
		dto.dataFine = entity.dataFine;
		
		return dto;
	}
	
}
