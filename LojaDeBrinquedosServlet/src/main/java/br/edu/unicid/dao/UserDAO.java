package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicid.model.User;
import br.edu.unicid.util.ConnectionFactory;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs; 
	private User user;
	
	
	public UserDAO() throws Exception {
		
		//Chama a classe ConnectionFactory e estabelece uma conexao
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("Erro: \n" + e.getMessage());
		}
	}
	
	// Metodo Salvar
	public void Salvar(User user) throws Exception {
		if (user == null)
			throw new Exception ("ERRO: O Objeto usuario nao pode ser nulo");
		try {
			String SQL = "INSERT INTO USERS (LOGIN, PASSWORD) VALUES "
					+ "(?, ?)";
			
			System.out.println("Login: " + user.getLogin());
			System.out.println("Senha: " + user.getPassword());
			ps = conn.prepareStatement(SQL);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
	} catch (SQLException sqle) {
		throw new Exception("ERRO: Problema ao salvar dados do usuario " + sqle);
	}
		finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo Excluir
	public void Excluir(User user) throws Exception {
		if (user == null)
			throw new Exception ("ERRO: O Objeto usuario nao pode ser nulo");
		try {
			String SQL = "DELETE FROM USERS WHERE ID = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, user.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao excluir o usuario " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo para Procurar um Usuario
	public User ProcurarUser(int idUser) throws Exception {

		try {
			String SQL = "SELECT * FROM LOJA_BRINQUEDOS.USERS WHERE ID=?;";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, idUser);			
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("ID");
				String login = rs.getString("LOGIN");
				String password = rs.getString("PASSWORD");
				
				user = new User(id, login, password);
			}
			return user;
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao procurar um usuario " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// Listar Todos os Usuarios
	public List<User> TodosUsers() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM LOJA_BRINQUEDOS.USERS;");
			rs = ps.executeQuery();
			List<User> list = new ArrayList<User>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String login = rs.getString("LOGIN");
				String password = rs.getString("PASSWORD");
				list.add(new User(id, login, password));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao listar todos os usuarios " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// Metodo para atualizar um usuarios
	public void Atualizar(User user) throws Exception {
		if (user == null)
			throw new Exception("ERRO: Atributos para Atualização de usuario invalidos");
		try {
			String SQL = "UPDATE USERS SET LOGIN=?, PASSWORD=? WHERE ID=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao atualizar o usuario " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	public User autentica(String login, String password) throws Exception {
        try {
            String SQL = "SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                return new User(id, login, password);
            } else {
                return null; // Usuário não encontrado ou senha incorreta.
            }
        } catch (SQLException sqle) {
            throw new Exception("ERRO: Problema ao autenticar o usuário " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
    }
	
	//Retorna a quantidade total de Categorias
	public int TotalUsuarios() throws Exception {
	    int totalUsuarios = 0;
	    
	    // Consulta SQL para contar brinquedos
	    String sqlCount = "SELECT COUNT(*) AS total FROM LOJA_BRINQUEDOS.USERS";

	    try (Connection connection = ConnectionFactory.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sqlCount);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	        	totalUsuarios = resultSet.getInt("total");
	        }
	    } catch (SQLException sqle) {
	    	throw new Exception("Erro ao contar o total de usuarios " + sqle);
	    }

	    return totalUsuarios;
	}
	
}