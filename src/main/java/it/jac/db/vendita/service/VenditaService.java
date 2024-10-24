package it.jac.db.vendita.service;

import java.time.LocalDateTime;
import java.util.List;

import it.jac.db.vendita.dto.VenditaDTO;

public interface VenditaService {

	VenditaDTO create(long idArticolo, long idMagazzino);

	VenditaDTO findVenditaById(long idVendita);

	List<VenditaDTO> findVenditeByArticolo(long idArticolo, LocalDateTime dataInizio, LocalDateTime dataFine);
	
	List<VenditaDTO> findVenditeByOfferta(long idPrezzoOfferta, LocalDateTime dataInizio, LocalDateTime dataFine);

}
