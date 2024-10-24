package it.jac.db.vendita.controller;

import it.jac.db.vendita.dto.StockInDTO;
import it.jac.db.vendita.service.MagazzinoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/magazzini")
public class MagazzinoController {
    private static final Logger log = LoggerFactory.getLogger(ArticoloController.class);
    @Inject
    private MagazzinoService service;

    @PUT
    @Path("/{id}/update-stock")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateStock(@PathParam("id")long idMagazzino,  StockInDTO dto){

        log.debug("richiamo update stock {}", dto);

        return Response.ok( this.service.updateStock(dto.getIdArticolo(), idMagazzino, dto.getNumPezzi())).build();
    }

}
