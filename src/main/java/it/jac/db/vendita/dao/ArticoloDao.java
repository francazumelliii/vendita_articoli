package it.jac.db.vendita.dao;

import java.util.List;
import java.util.Optional;

import it.jac.db.vendita.dto.ArticoloOffertaFilterDTO;
import it.jac.db.vendita.entity.Articolo;

public interface ArticoloDao {

	Articolo findById(long id);
	
	List<Articolo> findAll();
	
	List<Articolo> findInOfferta(ArticoloOffertaFilterDTO dto);
	
	long save(Articolo entity);
	
	void update(Articolo entity);
	
	void delete(Articolo entity);
	
	void delete(long id);

	void save(List<Articolo> list);
}
