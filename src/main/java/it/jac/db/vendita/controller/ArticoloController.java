package it.jac.db.vendita.controller;


import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.jac.db.vendita.dto.ArticoloOffertaFilterDTO;
import it.jac.db.vendita.dto.ArticoloOutDTO;
import it.jac.db.vendita.dto.PrezzoOffertaInDTO;
import it.jac.db.vendita.service.ArticoloService;
import jakarta.inject.Inject;

@Path("/articoli")
public class ArticoloController {

	private static final Logger log = LoggerFactory.getLogger(ArticoloController.class);
	
//	ci pensa il framework ad eseguire la dependency injection
	@Inject
	private ArticoloService service;
	
	@GET
	@Path("{id}")
	@Produces(value = "application/json")
	public ArticoloOutDTO findById(long id) {
		
		log.info("richiamato metodo findById. service [{}]", this.service);
		
		return this.service.findArticoloById(id);
	}
	
	@POST
	@Path("{id}")
	@Consumes(value = "application/json")
	@Produces(value = "application/json")
	public ArticoloOutDTO createOfferta(PrezzoOffertaInDTO dto) {

		return this.service.createPrezzoOfferta(dto);
	}
	
	@GET
	@Path("/offerte")
	@Produces(value = "application/json")
	public Response findInOfferta(@Valid @BeanParam ArticoloOffertaFilterDTO dto) {
		
		log.info("metodo di ricerca articoli in offerta");
		
		log.info("dataInizio [{}]", dto.getDataInizio());
		log.info("dataFine [{}]", dto.getDataInizio());
		log.info("pagina [{}]", dto.getPagina());
		log.info("dimPagina [{}]", dto.getDimPagina());
		log.info("ordina [{}]", dto.getOrdina());

		if(dto.getDataInizio().isAfter(dto.getDataFine())){
			return Response.status(Response.Status.BAD_REQUEST).entity("Data fine non valida").build();
		}
		
		return Response.ok(this.service.findArticoliOfferta(dto)).build();
	}
	
}
