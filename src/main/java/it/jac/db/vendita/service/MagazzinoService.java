package it.jac.db.vendita.service;

import java.util.List;

import it.jac.db.vendita.dto.StockInDTO;
import it.jac.db.vendita.dto.StockOutDTO;

public interface MagazzinoService {

	StockInDTO findStock(long idArticolo, long idMagazzino);

	StockOutDTO updateStock(long idArticolo, long idMagazzino, int numPezzi);

	List<StockInDTO> findStock(long idArticolo);

}
