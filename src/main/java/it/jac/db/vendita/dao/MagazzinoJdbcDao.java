package it.jac.db.vendita.dao;

import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.entity.Magazzino;
import it.jac.db.vendita.util.DatasourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static io.smallrye.config._private.ConfigLogging.log;

public class MagazzinoJdbcDao implements MagazzinoDao{
    private static Logger log = LoggerFactory.getLogger(ArticoloJdbcDao.class);

    @Override
    public Magazzino findById(long idMagazzino) {
        Magazzino entity = null;

        String sql = """
				
				SELECT id, cod, indirizzo
				FROM magazzino
				WHERE id = ?;
				""";
        log.info("sql [{}]", sql);
        try (Connection conn = DatasourceUtil.getConnection()) {

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idMagazzino);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                entity = new Magazzino();
                entity.id = rs.getLong("id");
                entity.cod = rs.getString("cod");
                entity.indirizzo = rs.getString("indirizzo");

            }
            log.info("entity id [{}]", entity.id);

        } catch(SQLException e) {

            throw new RuntimeException(e);
        }

        return entity;
    }
}
