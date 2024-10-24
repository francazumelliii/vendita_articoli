package it.jac.db.vendita.dao;

import java.time.LocalDate;
import java.util.List;

import it.jac.db.vendita.entity.PrezzoOfferta;

public interface PrezzoOffertaDao {

	List<PrezzoOfferta> findByArticolo(long idArticolo);

	boolean checkOfferte(long idArticolo, LocalDate dataInizio, LocalDate dataFine);

	void save(PrezzoOfferta offerta);
}
