package br.com.valemobi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public Connection getConnection() {

		try {

			//realiza a conexão com o banco 
			return DriverManager.getConnection("jdbc:mysql://localhost/Valemobi", "phpmyadmin", "R753901");

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}
