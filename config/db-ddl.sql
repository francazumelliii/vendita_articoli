-- Creazione della tabella Articolo
CREATE TABLE articolo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    modello VARCHAR(255) NOT NULL,
    descrizione TEXT,
    prezzo_base DECIMAL(10, 2) NOT NULL
);

-- Creazione della tabella PrezzoOfferta
CREATE TABLE prezzo_offerta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valore DECIMAL(10, 2) NOT NULL,
    data_inizio DATE NOT NULL,
    data_fine DATE NOT NULL,
    articolo_id BIGINT NOT NULL,
    FOREIGN KEY (articolo_id) REFERENCES articolo(id)
);
