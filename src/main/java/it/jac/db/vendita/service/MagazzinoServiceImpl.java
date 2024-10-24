package it.jac.db.vendita.service;

import java.util.List;
import java.util.Objects;

import it.jac.db.vendita.dao.*;
import it.jac.db.vendita.dto.StockInDTO;
import it.jac.db.vendita.dto.StockOutDTO;
import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.entity.Magazzino;
import it.jac.db.vendita.entity.Stock;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MagazzinoServiceImpl implements MagazzinoService {
	private MagazzinoDao magazzinoDao = new MagazzinoJdbcDao();
	private StockDao stockDao = new StockJdbcDao();

	private ArticoloDao articoloDao = new ArticoloJdbcDao();

	@Override
	public StockInDTO findStock(long idArticolo, long idMagazzino) {
		
		return null;
	}
	
	@Override
	public List<StockInDTO> findStock(long idArticolo) {
		
		return List.of();
	}
	
	@Override
	public StockOutDTO updateStock(long idArticolo, long idMagazzino, int numPezzi) {
		// lettura dell'articolo dato in input l' idArticolo
	  	Articolo articolo = this.articoloDao.findById(idArticolo);

	  	//TODO il client non comprende l'errore
		Objects.requireNonNull(articolo);

		// lettura del magazzino dato in input l' idMagazzino
		Magazzino magazzino = this.magazzinoDao.findById(idMagazzino);
		Objects.requireNonNull(magazzino);

		Boolean save = false;
		// leggere entity dallo stock
		Stock stock = this.stockDao.findById(articolo, magazzino);
		//  se presente aggiorno il numero di pezzi
		if(stock == null){

			stock = new Stock();
			stock.articolo = articolo;
			stock.magazzino = magazzino;
			save = true;
		}
		stock.numPezzi = numPezzi;

		if(save){
			this.stockDao.save(stock);
		}else{
			this.stockDao.update(stock);
		}
		return StockOutDTO.build(stock);


		
	}
	
	
}
