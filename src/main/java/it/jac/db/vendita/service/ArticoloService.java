package it.jac.db.vendita.service;

import java.util.List;

import it.jac.db.vendita.dto.*;

public interface ArticoloService {

	public ArticoloOutDTO findArticoloById(long idArticolo);

	List<ArticoloOutDTO> findAll();

	PaginaArticoli findArticoliOfferta(ArticoloOffertaFilterDTO dto);

	ArticoloOutDTO updateArticolo(ArticoloInDTO dto);

	ArticoloOutDTO createPrezzoOfferta(PrezzoOffertaInDTO dto);

	ArticoloOutDTO deletePrezzoOfferta(long idOfferta);

}
