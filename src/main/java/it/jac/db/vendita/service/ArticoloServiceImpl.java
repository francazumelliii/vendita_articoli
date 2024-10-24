package it.jac.db.vendita.service;

import java.util.ArrayList;
import java.util.List;

import it.jac.db.vendita.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.jac.db.vendita.dao.ArticoloDao;
import it.jac.db.vendita.dao.ArticoloJdbcDao;
import it.jac.db.vendita.dao.PrezzoOffertaDao;
import it.jac.db.vendita.dao.PrezzoOffertaJdbcDao;
import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.entity.PrezzoOfferta;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticoloServiceImpl implements ArticoloService {

	private static Logger log = LoggerFactory.getLogger(ArticoloServiceImpl.class);
	
	private ArticoloDao dao = new ArticoloJdbcDao();
	private PrezzoOffertaDao offertaDao = new PrezzoOffertaJdbcDao();
	private PaginaArticoli paginaArticoli = new PaginaArticoli();
	
	@Override
	public List<ArticoloOutDTO> findAll() {
		
		List<ArticoloOutDTO> result = new ArrayList<>();
		
		List<Articolo> list = this.dao.findAll();
		
		list.forEach(entity -> {
			
//			TODO creare istanza di DTO ed inserirlo nella lista
			
		});
		return result;
	}

	@Override
	public ArticoloOutDTO findArticoloById(long idArticolo) {
		
		Articolo entity = dao.findById(idArticolo);
		
//		devo recuperare l'elenco delle offerte di un articolo
		List<PrezzoOfferta> listOfferte = this.offertaDao.findByArticolo(idArticolo);
		log.info("numero di offerte {}", listOfferte.size());
		entity.offerte.addAll(listOfferte);
		
		return ArticoloOutDTO.build(entity);
	}

	
	@Override
	public PaginaArticoli findArticoliOfferta(ArticoloOffertaFilterDTO dto) {

		PaginaArticoli result = new PaginaArticoli();

		List<Articolo> entityList = this.dao.findInOfferta(dto);
		List<ArticoloOutDTO> dtoList = new ArrayList<>();

		entityList.forEach(entity ->{
			ArticoloOutDTO articoloDto = ArticoloOutDTO.build(entity);
			dtoList.add(articoloDto);
		});
		
		log.info("numero di articoli trovati {}", entityList.size());
		result.addFilter("dataInizio", dto.getDataInizio());
		result.addFilter("dataFine", dto.getDataFine());
		result.addAll(dtoList);
		result.setPagina(dto.getPagina());
		result.setDimPagina(dto.getDimPagina());
		result.setOrdina(dto.getOrdina());

		return result;
	}
	
	@Override
	public ArticoloOutDTO updateArticolo(ArticoloInDTO dto) {
		
		return null;
	}

	@Override
	public ArticoloOutDTO createPrezzoOfferta(PrezzoOffertaInDTO dto) {

//		check presenza articolo
		
		Articolo entity = dao.findById(dto.getIdArticolo());
		if (entity == null) {
			throw new RuntimeException("Articolo [" + dto.getIdArticolo() + "] non presente");
		}
		
//		check date
		if (dto.getDataInizio().equals(dto.getDataFine())) {
			throw new RuntimeException("Date non valide");
		}
		if (dto.getDataInizio().isAfter(dto.getDataFine())) {
			throw new RuntimeException("Date non valide");
		}
		
//		check valore
		if (dto.getValore() <= 0) {
			throw new RuntimeException("Valore [" + dto.getValore() + "] non valido");
		}
		
//		check offerte no collisioni
		if (!this.offertaDao.checkOfferte(dto.getIdArticolo(), dto.getDataInizio(), dto.getDataFine())) {
			throw new RuntimeException("Presenti offerte non compatibili");
		}
		
//		creo l'entita', salvando a DB il nuovo record
		PrezzoOfferta newEntity = new PrezzoOfferta();
		newEntity.articolo = entity;
		newEntity.valore = dto.getValore();
		newEntity.dataInizio = dto.getDataInizio();
		newEntity.dataFine = dto.getDataFine();
		
		this.offertaDao.save(newEntity);
		
		return findArticoloById(dto.getIdArticolo());
	}
	
	@Override
	public ArticoloOutDTO deletePrezzoOfferta(long idOfferta) {

		return null;
	}
}
