package it.jac.db.vendita.dto;

import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.entity.Stock;

public class StockOutDTO {
   private ArticoloOutDTO articolo;
   private MagazzinoOutDTO magazzino;

    private int numPezzi;


    private StockOutDTO(){}


    private StockOutDTO(MagazzinoOutDTO magazzino, ArticoloOutDTO articolo, int numPezzi){
        this.articolo = articolo;
        this.magazzino = magazzino;
        this.numPezzi = numPezzi;

    }

    public MagazzinoOutDTO getMagazzino() {
        return magazzino;
    }

    public void setIdMagazzino(MagazzinoOutDTO magazzino) {
        this.magazzino = magazzino;
    }

    public ArticoloOutDTO getArticolo() {
        return articolo;
    }

    public void setIdArticolo(ArticoloOutDTO articolo) {
        this.articolo = articolo;
    }

    public int getNumPezzi() {
        return numPezzi;
    }

    public void setNumPezzi(int numPezzi) {
        this.numPezzi = numPezzi;
    }


    public static StockOutDTO build(Stock stock){
        StockOutDTO dto = new StockOutDTO();
        dto.articolo = ArticoloOutDTO.build(stock.articolo);
        dto.magazzino = MagazzinoOutDTO.build(stock.magazzino);
        return dto;
    }
}
