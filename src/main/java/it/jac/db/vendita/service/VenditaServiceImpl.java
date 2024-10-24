package it.jac.db.vendita.service;

import java.time.LocalDateTime;
import java.util.List;

import it.jac.db.vendita.dto.VenditaDTO;

public class VenditaServiceImpl implements VenditaService {

	@Override
	public VenditaDTO create(long idArticolo, long idMagazzino) {
		
		return null;
	}

	@Override
	public VenditaDTO findVenditaById(long idVendita) {
		
		return null;
	}
	
	@Override
	public List<VenditaDTO> findVenditeByArticolo(long idArticolo, LocalDateTime dataInizio, LocalDateTime dataFine) {
		
		return List.of();
	}
	
	@Override
	public List<VenditaDTO> findVenditeByOfferta(long idPrezzoOfferta, LocalDateTime dataInizio, LocalDateTime dataFine) {
		
		return List.of();
	}
}
