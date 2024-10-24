package it.jac.db.vendita.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.jac.db.vendita.entity.PrezzoOfferta;
import it.jac.db.vendita.util.DatasourceUtil;

public class PrezzoOffertaJdbcDao implements PrezzoOffertaDao {

	private static Logger log = LoggerFactory.getLogger(PrezzoOffertaJdbcDao.class);

	@Override
	public List<PrezzoOfferta> findByArticolo(long idArticolo) {
		
		List<PrezzoOfferta> result = new ArrayList<>();
		
		String sql = """
				SELECT id, valore, data_inizio, data_fine, articolo_id
				FROM prezzo_offerta
				WHERE articolo_id = ?;
				""";
		
		log.debug("sql [{}]", sql);
		
		try (Connection conn = DatasourceUtil.getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, idArticolo);
			
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
								
				PrezzoOfferta entity = new PrezzoOfferta();
				entity.id = rs.getLong("id");
				entity.valore = rs.getDouble("valore");
				entity.dataInizio = rs.getDate("data_inizio").toLocalDate();
				entity.dataFine = rs.getDate("data_fine").toLocalDate();
				
				log.debug("entity found {}", entity.id);
				
				result.add(entity);
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		return result;
	}

	@Override
	public boolean checkOfferte(long idArticolo, LocalDate dataInizio, LocalDate dataFine) {

		boolean result = false;
		
		String sql = """
				SELECT 1
				FROM prezzo_offerta
				WHERE articolo_id=?
				AND (data_inizio BETWEEN ? AND ?
				OR data_fine BETWEEN ? AND ?
				OR (data_inizio < ? AND data_fine > ?))				
				""";
		
		try (Connection conn = DatasourceUtil.getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setLong(i++, idArticolo);
			pstm.setDate(i++, Date.valueOf(dataInizio));
			pstm.setDate(i++, Date.valueOf(dataFine));
			pstm.setDate(i++, Date.valueOf(dataInizio));
			pstm.setDate(i++, Date.valueOf(dataFine));
			pstm.setDate(i++, Date.valueOf(dataInizio));
			pstm.setDate(i++, Date.valueOf(dataFine));
			
			ResultSet rs = pstm.executeQuery();
			
			result = !rs.next();
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		return result;
	}

	@Override
	public void save(PrezzoOfferta offerta) {
		
		String sql = """
				INSERT INTO prezzo_offerta(valore, data_inizio, data_fine, articolo_id)
				VALUES(?, ?, ?, ?)
				""";
		
		try (Connection conn = DatasourceUtil.getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setDouble(i++, offerta.valore);
			pstm.setDate(i++, Date.valueOf(offerta.dataInizio));
			pstm.setDate(i++, Date.valueOf(offerta.dataFine));
			pstm.setLong(i++, offerta.articolo.id);

//			avvio comandi SQL INSERT, UPDATE, DELETE
			pstm.executeUpdate();
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
		}

	}

	
}
