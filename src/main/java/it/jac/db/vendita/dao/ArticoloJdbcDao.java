package it.jac.db.vendita.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.jac.db.vendita.dto.ArticoloOffertaFilterDTO;
import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.util.DatasourceUtil;

public class ArticoloJdbcDao implements ArticoloDao {

	private static Logger log = LoggerFactory.getLogger(ArticoloJdbcDao.class);
	
	@Override
	public Articolo findById(long id) {
		
		Articolo entity = null;
		
		String sql = """
				
				SELECT id, marca, modello, descrizione, prezzo_base
				FROM articolo
				WHERE id = ?;
				""";
		
		log.debug("sql [{}]", sql);
		
		try (Connection conn = DatasourceUtil.getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, id);
			
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
								
				entity = new Articolo();
				entity.id = rs.getLong("id");
				entity.marca = rs.getString("marca");
				entity.modello = rs.getString("modello");
				entity.descrizione = rs.getString("descrizione");
				entity.prezzoBase = rs.getDouble("prezzo_base");
				
				log.debug("entity found {}", entity.id);
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		return entity;
	}

	@Override
	public List<Articolo> findAll() {
		return null;
	}

	@Override
	public List<Articolo> findInOfferta(ArticoloOffertaFilterDTO dto) {
		
		String sql1 = """
				
				SELECT * 
				FROM articolo a
				where (
					select count(*) 
				    from prezzo_offerta o
				    where o.articolo_id = a.id
				) > 0
				""";
		
		String sql2 = """
				
				SELECT distinct a.* 
				FROM articolo a INNER JOIN prezzo_offerta o ON a.id = o.articolo_id
				WHERE (data_inizio BETWEEN ? AND ?) OR
				(data_fine BETWEEN ? AND ?)
				ORDER BY ?
				LIMIT ?, ?
				""";
		
		String sql3 = """
				
				SELECT * 
				FROM articolo a
				where exists (
					select 1 
				    from prezzo_offerta o
				    where o.articolo_id = a.id
				)
				""";
		
		String sql4 = """
				
				select *
				from articolo
				where id in (
					select articolo_id
					from prezzo_offerta
				)				
				""";
		
		List<Articolo> result = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT distinct a.id, a.marca, a.modello, a.descrizione,");
		sb.append(" a.prezzo_base, count(*) over() conteggio_totale");
		sb.append(" FROM articolo a INNER JOIN prezzo_offerta o ON a.id = o.articolo_id");
		sb.append(" WHERE (data_inizio BETWEEN ? AND ?) OR");
		sb.append(" (data_fine BETWEEN ? AND ?)");
		if (dto.getOrdina() != null && !dto.getOrdina().isBlank()) {			
			sb.append(" ORDER BY ?");
		}
		sb.append(" LIMIT ?, ?");
		
		int idx = ((dto.getPagina() - 1) * dto.getDimPagina()) + 1;
		
		try (Connection conn = DatasourceUtil.getConnection()) {
			
			log.debug("SQL={}", sb.toString());
			log.debug("IDX={}", idx);
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			int i = 1;
			pstm.setDate(i++, Date.valueOf(dto.getDataInizio()));
			pstm.setDate(i++, Date.valueOf(dto.getDataFine()));
			pstm.setDate(i++, Date.valueOf(dto.getDataInizio()));
			pstm.setDate(i++, Date.valueOf(dto.getDataFine()));
			if (dto.getOrdina() != null && !dto.getOrdina().isBlank()) {
				pstm.setString(i++, dto.getOrdina());
			}
			pstm.setInt(i++, idx);
			pstm.setInt(i++, dto.getDimPagina());
			
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
								
				Articolo entity = new Articolo();
				entity.id = rs.getLong("id");
				entity.marca = rs.getString("marca");
				entity.modello = rs.getString("modello");
				entity.descrizione = rs.getString("descrizione");
				entity.prezzoBase = rs.getDouble("prezzo_base");
				entity.totaleRecord = rs.getInt("conteggio_totale");
				
				log.debug("entity found {}", entity.id);
				
				result.add(entity);
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
		}

		return result;
	}

	@Override
	public void save(List<Articolo> list) {
		
		String sql = """
				
				INSERT INTO articolo (marca, modello, descrizione, prezzo_base)
				VALUES(?, ?, ?, ?)
				""";
		
		log.debug("sql [{}]", sql);
		
		try (Connection conn = DatasourceUtil.getConnection()) {
			
			conn.setAutoCommit(false);
			
//			potrebbe rallentare il salvataggio???
			PreparedStatement pstm = conn.prepareStatement(sql);

			for(Articolo entity : list) {
				
				int i = 1;
				pstm.setString(i++, entity.marca);
				pstm.setString(i++, entity.modello);
				pstm.setString(i++, entity.descrizione);
				pstm.setDouble(i++, entity.prezzoBase);
	
				pstm.addBatch();
				
//				ResultSet rs = pstm.getGeneratedKeys();
//				if (rs.next()) {
//					entity.id = rs.getLong(1);
//				}
			}
			pstm.executeBatch();
			conn.commit();
			System.out.println("execute batch");
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
		}

	}

	
	@Override
	public long save(Articolo entity) {
		
		String sql = """
				
				INSERT INTO articolo (marca, modello, descrizione, prezzo_base)
				VALUES(?, ?, ?, ?)
				""";
		
		log.debug("sql [{}]", sql);
		
		try (Connection conn = DatasourceUtil.getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pstm.setString(i++, entity.marca);
			pstm.setString(i++, entity.modello);
			pstm.setString(i++, entity.descrizione);
			pstm.setDouble(i++, entity.prezzoBase);

			pstm.executeUpdate();
			
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				entity.id = rs.getLong(1);
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
		}

		return entity.id;
	}

	@Override
	public void update(Articolo entity) {
		
	}

	@Override
	public void delete(Articolo entity) {
		
	}

	@Override
	public void delete(long id) {
		
	}

}
