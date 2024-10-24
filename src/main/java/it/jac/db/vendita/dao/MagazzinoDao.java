package it.jac.db.vendita.dao;

import it.jac.db.vendita.entity.Magazzino;

public interface MagazzinoDao {


    Magazzino findById(long idMagazzino);
}
