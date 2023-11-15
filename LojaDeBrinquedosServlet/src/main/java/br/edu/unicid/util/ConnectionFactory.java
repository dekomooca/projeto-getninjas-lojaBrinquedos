package br.edu.unicid.util;

import java.sql.*;

public class ConnectionFactory {

	public static Connection getConnection() throws Exception {

		try {

			// Indica o banco de dados e o driver a ser utilizado

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			// Estabelece a conexao e retorna uma conexao
			String login = "root";
			String senha = "root";
			String url = "jdbc:mysql://localhost:3306/loja_brinquedos?useUnicode=true&characterEncoding=UTF-8";

			return DriverManager.getConnection(url,login,senha);



		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Fecha uma conexao de tres formas: conn, stmt, rs

	public static void closeConnection(Connection conn, Statement stmt,
			ResultSet rs) throws Exception {
		close(conn, stmt, rs);
	}

	public static void closeConnection(Connection conn, Statement stmt)
			throws Exception {
		close(conn, stmt, null);
	}

	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}

	private static void close(Connection conn, Statement stmt, ResultSet rs)
			throws Exception {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
