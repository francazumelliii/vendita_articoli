package it.jac.db.vendita.dto;

public class StockInDTO {
    private long idArticolo;
    private int numPezzi;



    public StockInDTO(){

    }

    public StockInDTO(long idArticolo, int numPezzi) {
        this.idArticolo = idArticolo;
        this.numPezzi = numPezzi;
    }

    public long getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(long idArticolo) {
        this.idArticolo = idArticolo;
    }

    public int getNumPezzi() {
        return numPezzi;
    }

    public void setNumPezzi(int numPezzi) {
        this.numPezzi = numPezzi;
    }
}
