package it.jac.db.vendita.dao;

import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.entity.Magazzino;
import it.jac.db.vendita.entity.Stock;
import it.jac.db.vendita.util.DatasourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockJdbcDao implements StockDao {
    private static Logger log = LoggerFactory.getLogger(ArticoloJdbcDao.class);
    @Override
    public Stock findById(Articolo articolo, Magazzino magazzino) {
        Stock entity = null;

        String sql = """
				
				SELECT  magazzino_id, num_pezzi, articolo_id
				FROM stock
				WHERE magazzino_id = ? AND articolo_id = ?;
				""";
        log.info("sql [{}]", sql);
        try (Connection conn = DatasourceUtil.getConnection()) {

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, magazzino.id);
            pstm.setLong(2, articolo.id);


            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                entity = new Stock();
                entity.numPezzi = rs.getInt("num_pezzi");
                entity.articolo = articolo;
                entity.magazzino = magazzino;
            }
            log.info("entity id [{}]", articolo.id,  magazzino.id);

        } catch(SQLException e) {

            throw new RuntimeException(e);
        }

        return entity;
    }

    @Override
    public void save(Stock stock) {
        String sql = """
				
				INSERT INTO stock (magazzino_id, articolo_id, num_pezzi)
				VALUES (?, ?, ?)
				""";
        log.info("sql [{}]", sql);
        try (Connection conn = DatasourceUtil.getConnection()) {

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, stock.magazzino.id);
            pstm.setLong(2, stock.articolo.id);
            pstm.setInt(3, stock.numPezzi);

            pstm.executeUpdate();

        } catch(SQLException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Stock stock) {
        String sql = """
				
				UPDATE stock SET num_pezzi = ? 
				WHERE articolo_id = ? AND magazzino_id = ?
	
				""";
        log.info("sql [{}]", sql);
        try (Connection conn = DatasourceUtil.getConnection()) {

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, stock.numPezzi);
            pstm.setLong(2, stock.articolo.id);
            pstm.setLong(3, stock.magazzino.id);

            pstm.executeUpdate();

        } catch(SQLException e) {

            throw new RuntimeException(e);
        }

    }


}
