package it.jac.db.vendita.dao;

import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.entity.Magazzino;
import it.jac.db.vendita.entity.Stock;

public interface StockDao {


    Stock findById(Articolo articolo, Magazzino magazzino);

    void save(Stock stock);

    void update(Stock stock);
}
