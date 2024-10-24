package it.jac.db.vendita.dto;

import it.jac.db.vendita.entity.Magazzino;

public class MagazzinoOutDTO {

    private String cod;
    private String indirizzo;


    private MagazzinoOutDTO(){}
    private MagazzinoOutDTO(String cod, String indirizzo){
        this.cod = cod;
        this.indirizzo = indirizzo;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public static MagazzinoOutDTO build(Magazzino magazzino){
        MagazzinoOutDTO dto = new MagazzinoOutDTO();
        dto.setCod(magazzino.cod);
        dto.setIndirizzo(magazzino.indirizzo);

        return dto;
    }
}
