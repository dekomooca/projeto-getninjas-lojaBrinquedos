package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicid.model.Brinquedo;
import br.edu.unicid.util.ConnectionFactory;

public class BrinquedoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs; 
	private Brinquedo brinquedo;
	
	
	public BrinquedoDAO() throws Exception {
		
		//Chama a classe ConnectionFactory e estabelece uma conexao
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("Erro: \n" + e.getMessage());
		}
	}
	
	// Metodo Salvar
	public void Salvar(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception ("ERRO: O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO BRINQUEDOS (ID, DESCRICAO, ID_CATEGORIA, MARCA, URL_FOTO, VALOR, DETALHES) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, brinquedo.getId());
			ps.setString(2, brinquedo.getDescricao());
			ps.setInt(3, brinquedo.getId_categoria());
			ps.setString(4, brinquedo.getMarca());
			ps.setString(5, brinquedo.getUrl_foto());
			ps.setFloat(6, brinquedo.getValor());
			ps.setString(7, brinquedo.getDetalhes());
			ps.executeUpdate();
	} catch (SQLException sqle) {
		throw new Exception("ERRO: Problema ao salvar dados do brinquedo " + sqle);
	}
		finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo Excluir
	public void Excluir(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception ("O valor passado nao pode ser nulo");
		try {
			String SQL = "DELETE FROM LOJA_BRINQUEDOS.BRINQUEDOS WHERE ID = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, brinquedo.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao excluir o brinquedo " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo Excluir Brinquedos por Categoria
	public void ExcluirPorCategoria(int id_categoria) throws Exception {
		int categoria = id_categoria;
		if (id_categoria <= 0)
			throw new Exception ("O valor o codigo da categoria é inválido");
		try {
			String SQL = "DELETE FROM LOJA_BRINQUEDOS.BRINQUEDOS WHERE ID_CATEGORIA = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, categoria);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao excluir todos brinquedos da categoria " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo para Procurar Brinquedo
	public Brinquedo ProcurarBrinquedo(int idBrinquedo) throws Exception {

		try {
			String SQL = "SELECT BRIN.ID, BRIN.DESCRICAO, BRIN.ID_CATEGORIA, BRIN.MARCA, BRIN.URL_FOTO, BRIN.VALOR, BRIN.DETALHES, CAT.NOME"
					+ " FROM LOJA_BRINQUEDOS.BRINQUEDOS AS BRIN"
					+ " INNER JOIN LOJA_BRINQUEDOS.CATEGORIAS AS CAT ON CAT.ID = BRIN.ID_CATEGORIA"
					+ " WHERE BRIN.ID=?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, idBrinquedo);			
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("ID");
				String descricao = rs.getString("DESCRICAO");
				int id_categoria = rs.getInt("ID_CATEGORIA");
				String marca = rs.getString("MARCA");
				String url_foto = rs.getString("URL_FOTO");
				float valor = rs.getFloat("VALOR");
				String detalhes = rs.getString("DETALHES");
				String nome_categoria = rs.getString("NOME");
				
				brinquedo = new Brinquedo(id, descricao, id_categoria, marca, url_foto, valor, detalhes, nome_categoria);
			}
			return brinquedo;
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao procurar um brinquedo " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// Listar todos os Brinquedos
	public List<Brinquedo> TodosBrinquedos() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT BRIN.ID, BRIN.DESCRICAO, BRIN.ID_CATEGORIA, BRIN.MARCA, BRIN.URL_FOTO, BRIN.VALOR, BRIN.DETALHES, CAT.NOME"
					+ " FROM LOJA_BRINQUEDOS.BRINQUEDOS AS BRIN"
					+ " INNER JOIN LOJA_BRINQUEDOS.CATEGORIAS AS CAT ON CAT.ID = BRIN.ID_CATEGORIA");
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String descricao = rs.getString("DESCRICAO");
				int id_categoria = rs.getInt("ID_CATEGORIA");
				String marca = rs.getString("MARCA");
				String url_foto = rs.getString("URL_FOTO");
				float valor = rs.getFloat("VALOR");
				String detalhes = rs.getString("DETALHES");
				String nome_categoria = rs.getString("NOME");
				list.add(new Brinquedo(id, descricao, id_categoria, marca, url_foto, valor, detalhes, nome_categoria));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao listar todos os brinquedos " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// Metodo para atualizar um brinquedo sem foto
	public void AtualizarSemFoto(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception("Atributos para Atualização invalidos");
		try {
			String SQL = "UPDATE BRINQUEDOS SET DESCRICAO=?, ID_CATEGORIA=?, MARCA=?, VALOR=?, DETALHES=? WHERE ID=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, brinquedo.getDescricao());
			ps.setInt(2, brinquedo.getId_categoria());
			ps.setString(3, brinquedo.getMarca());
			ps.setFloat(4, brinquedo.getValor());
			ps.setString(5, brinquedo.getDetalhes());
			ps.setInt(6, brinquedo.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao atualizar o brinquedo sem foto " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo para atualizar um brinquedo com foto
	public void AtualizarComFoto(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception("Atributos para Atualização invalidos");
		try {
			String SQL = "UPDATE BRINQUEDOS SET DESCRICAO=?, ID_CATEGORIA=?, URL_FOTO=?, MARCA=?, VALOR=?, DETALHES=? WHERE ID=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, brinquedo.getDescricao());
			ps.setInt(2, brinquedo.getId_categoria());
			ps.setString(3, brinquedo.getUrl_foto());
			ps.setString(4, brinquedo.getMarca());
			ps.setFloat(5, brinquedo.getValor());
			ps.setString(6, brinquedo.getDetalhes());
			ps.setInt(7, brinquedo.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao atualizar o brinquedo com foto " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Metodo para Listar os ultimos 6 Brinquedos
	public List<Brinquedo> DestaqueBrinquedos() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT BRIN.ID, BRIN.DESCRICAO, BRIN.ID_CATEGORIA, BRIN.MARCA, BRIN.URL_FOTO, BRIN.VALOR, BRIN.DETALHES, CAT.NOME"
					+ " FROM LOJA_BRINQUEDOS.BRINQUEDOS AS BRIN"
					+ " INNER JOIN LOJA_BRINQUEDOS.CATEGORIAS AS CAT ON CAT.ID = BRIN.ID_CATEGORIA"
					+ " ORDER BY ID DESC LIMIT 6");
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String descricao = rs.getString("DESCRICAO");
				int id_categoria = rs.getInt("ID_CATEGORIA");
				String marca = rs.getString("MARCA");
				String url_foto = rs.getString("URL_FOTO");
				float valor = rs.getFloat("VALOR");
				String detalhes = rs.getString("DETALHES");
				String nome_categoria = rs.getString("NOME");
				list.add(new Brinquedo(id, descricao, id_categoria, marca, url_foto, valor, detalhes, nome_categoria));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao listar todos os 06 ultimos brinquedos " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// Metodo para Listar os Brinquedos por Categoria
	public List<Brinquedo> BrinquedosCategoria(int idCategoria) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT BRIN.ID, BRIN.DESCRICAO, BRIN.ID_CATEGORIA, BRIN.MARCA, BRIN.URL_FOTO, BRIN.VALOR, BRIN.DETALHES, CAT.NOME"
					+ " FROM LOJA_BRINQUEDOS.BRINQUEDOS AS BRIN"
					+ " INNER JOIN LOJA_BRINQUEDOS.CATEGORIAS AS CAT ON CAT.ID = BRIN.ID_CATEGORIA"
					+ " WHERE CAT.ID = ?");
			ps.setInt(1, idCategoria);			
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String descricao = rs.getString("DESCRICAO");
				int id_categoria = rs.getInt("ID_CATEGORIA");
				String marca = rs.getString("MARCA");
				String url_foto = rs.getString("URL_FOTO");
				float valor = rs.getFloat("VALOR");
				String detalhes = rs.getString("DETALHES");
				String nome_categoria = rs.getString("NOME");
				list.add(new Brinquedo(id, descricao, id_categoria, marca, url_foto, valor, detalhes, nome_categoria));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception("ERRO: Problema ao listar todos os brinquedos por categoria " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	//Retorna o ultimo brinqeudo criado e soma + 1 no id
	public Brinquedo ultimoBrinquedo() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT BRIN.ID, BRIN.DESCRICAO, BRIN.ID_CATEGORIA, BRIN.MARCA, BRIN.URL_FOTO, BRIN.VALOR, BRIN.DETALHES, CAT.NOME"
					+ " FROM LOJA_BRINQUEDOS.BRINQUEDOS AS BRIN"
					+ " INNER JOIN LOJA_BRINQUEDOS.CATEGORIAS AS CAT ON CAT.ID = BRIN.ID_CATEGORIA"
					+ " ORDER BY ID DESC LIMIT 1");
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("ID");
				String descricao = rs.getString("DESCRICAO");
				int id_categoria = rs.getInt("ID_CATEGORIA");
				String marca = rs.getString("MARCA");
				String url_foto = rs.getString("URL_FOTO");
				float valor = rs.getFloat("VALOR");
				String detalhes = rs.getString("DETALHES");
				String nome_categoria = rs.getString("NOME");
				id = id +1;
				brinquedo = new Brinquedo(id, descricao, id_categoria, marca, url_foto, valor, detalhes, nome_categoria);
			}
			return brinquedo;
		} catch (SQLException sqle) {
			throw new Exception("Erro ao selecionar ultima categoria criada " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Retorna a quantidade total de brinquedos
	public int TotalBrinquedos() throws Exception {
	    int totalBrinquedos = 0;
	    
	    // Consulta SQL para contar brinquedos
	    String sqlCount = "SELECT COUNT(*) AS total FROM LOJA_BRINQUEDOS.BRINQUEDOS";

	    try (Connection connection = ConnectionFactory.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sqlCount);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	            totalBrinquedos = resultSet.getInt("total");
	        }
	    } catch (SQLException sqle) {
	    	throw new Exception("Erro ao contar o total de brinquedos " + sqle);
	    }

	    return totalBrinquedos;
	}
}
