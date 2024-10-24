package it.jac.db.vendita.dto;

import java.util.ArrayList;
import java.util.List;

import it.jac.db.vendita.entity.Articolo;

public class ArticoloOutDTO {

	public long id;
	public String marca;
	public String modello;
	public String descrizione;
	public double prezzoBase;

	public List<PrezzoOffertaOutDTO> offerte = new ArrayList<>();
	
	private ArticoloOutDTO() {
		
	}
	
//	factory method
	public static ArticoloOutDTO build(Articolo entity) {
		
		ArticoloOutDTO dto = new ArticoloOutDTO();
		
		dto.id = entity.id;
		dto.marca = entity.marca;
		dto.modello = entity.modello;
		dto.descrizione = entity.descrizione;
		dto.prezzoBase = entity.prezzoBase;
		
//		per ogni PrezzoOfferta devo creare il relativo DTO
//		e inserirlo nella lista
		entity.offerte.forEach(o -> {
			dto.offerte.add(PrezzoOffertaOutDTO.build(o));
		});
		
		return dto;
	}
	
}
