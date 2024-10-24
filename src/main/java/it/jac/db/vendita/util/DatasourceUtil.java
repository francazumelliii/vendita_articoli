package it.jac.db.vendita.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatasourceUtil {

	private static HikariDataSource dataSource;
	
	static {
		
		HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/vendita-articoli");
        config.setUsername("root");
        config.setPassword("mysql");

        // Opzioni di configurazione aggiuntive
        config.setMaximumPoolSize(2); // Numero massimo di connessioni nel pool
        config.setMinimumIdle(2); // Numero minimo di connessioni inattive
        config.setConnectionTimeout(30000); // Timeout per ottenere una connessione (in millisecondi)
        config.setIdleTimeout(600000); // Timeout per connessioni inattive
        config.setMaxLifetime(1800000); // Massima durata di una connessione nel pool

        // Creazione del DataSource
        dataSource = new HikariDataSource(config);

	}
	
	public static Connection getConnection() throws SQLException {
		
        return dataSource.getConnection();
	}
}
