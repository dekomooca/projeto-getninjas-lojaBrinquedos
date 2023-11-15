package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicid.model.Categoria;
import br.edu.unicid.util.ConnectionFactory;

public class CategoriaDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs; 
	private Categoria categoria;
	
	public CategoriaDAO() throws Exception {
		
		//Chama a classe ConnectionFactory e estabelece uma conexao
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("Erro: \n" + e.getMessage());
		}
	}
	
	// Metodo Salvar
	public void Salvar(Categoria categoria) throws Exception {
		if (categoria == null)
			throw new Exception ("O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO CATEGORIAS (ID, NOME, URL_FOTO) VALUES (?, ?, ?)";
			
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, categoria.getId());
			ps.setString(2, categoria.getNome());
			ps.setString(3, categoria.getUrl_foto());
			ps.executeUpdate();
	} catch (SQLException sqle) {
		throw new Exception("Erro ao salvar dados da categoria " + sqle);
	}
		finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo Excluir
	public void Excluir(Categoria categoria) throws Exception {
		if (categoria == null)
			throw new Exception ("O valor passado nao pode ser nulo");
		try {
			String SQL = "DELETE FROM CATEGORIAS WHERE ID = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, categoria.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir os dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo para Procurar Categorias
	public Categoria ProcurarCategoria(int idCategoria) throws Exception {
		try {
			String SQL = "SELECT  * FROM CATEGORIAS WHERE ID=?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, idCategoria);			
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("ID");
				String nome = rs.getString("NOME");
				String url_foto = rs.getString("URL_FOTO");
				
				categoria = new Categoria(id, nome, url_foto);
			}
			return categoria;
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir ao procurar dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// Listar todos as Categorias
	public List<Categoria> TodasCategorias() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM CATEGORIAS");
			rs = ps.executeQuery();
			List<Categoria> list = new ArrayList<Categoria>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String nome = rs.getString("NOME");
				String url_foto = rs.getString("URL_FOTO");
				list.add(new Categoria(id, nome, url_foto));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir ao listar categorias " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// Metodo para atualizar uma categoria sem foto
	public void AtualizarSemFoto(Categoria categoria) throws Exception {
		if (categoria == null)
			throw new Exception("Atributos para Atualização invalidos");
		try {
			String SQL = "UPDATE CATEGORIAS SET NOME=? WHERE ID=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, categoria.getNome());
			ps.setInt(2, categoria.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao atualizar a categoria " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo para atualizar uma categoria com foto
	public void AtualizarComFoto(Categoria categoria) throws Exception {
		if (categoria == null)
			throw new Exception("Atributos para Atualização invalidos");
		try {
			String SQL = "UPDATE CATEGORIAS SET NOME=?, URL_FOTO=? WHERE ID=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getUrl_foto());
			ps.setInt(3, categoria.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao atualizar a categoria " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	//Retorna a ultima categoria criada e soma + 1 no id
	public Categoria ultimaCategoria() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM LOJA_BRINQUEDOS.CATEGORIAS ORDER BY ID DESC LIMIT 1;");
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("ID");
				id = id + 1;
				String nome = rs.getString("NOME");
				String url_foto = rs.getString("URL_FOTO");
				
				categoria = new Categoria(id, nome, url_foto);
			}
			return categoria;
		} catch (SQLException sqle) {
			throw new Exception("Erro ao selecionar ultima categoria criada " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	//Retorna a quantidade total de Categorias
	public int TotalCategorias() throws Exception {
	    int totalCategorias = 0;
	    
	    // Consulta SQL para contar brinquedos
	    String sqlCount = "SELECT COUNT(*) AS total FROM LOJA_BRINQUEDOS.CATEGORIAS";

	    try (Connection connection = ConnectionFactory.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sqlCount);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	        	totalCategorias = resultSet.getInt("total");
	        }
	    } catch (SQLException sqle) {
	    	throw new Exception("Erro ao contar o total de categorias " + sqle);
	    }

	    return totalCategorias;
	}
	
	//Retorna a quantidade total de Brinquedos por categoria
	public int TotalBrinquedoPorCategoria(int id_categoria) throws Exception {
	    int totalBrinquedoPorCategoria = 0;
	    int categoria = id_categoria;
	    // Consulta SQL para contar brinquedos
	    String sqlCount = "SELECT COUNT(*) AS total FROM LOJA_BRINQUEDOS.BRINQUEDOS WHERE ID_CATEGORIA = ?";
	    ps = conn.prepareStatement(sqlCount);
	    ps.setInt(1, categoria);
	    try (Connection connection = ConnectionFactory.getConnection();
	         ResultSet resultSet = ps.executeQuery()) {

	        if (resultSet.next()) {
	        	totalBrinquedoPorCategoria = resultSet.getInt("total");
	        }
	    } catch (SQLException sqle) {
	    	throw new Exception("Erro ao contar o total de brinquedos por categoria " + sqle);
	    }

	    return totalBrinquedoPorCategoria;
	}
}