package br.edu.unicid.model;

public class Brinquedo {
	private int id;
	private String descricao;
	private int id_categoria;
	private String marca;
	private String url_foto;
	private Float valor;
	private String detalhes;
	private String nome_categoria;
	
	//Metodo Construtor
	public Brinquedo(int id, String descricao, int id_categoria, String marca, String url_foto, Float valor, String detalhes, String nome_categoria) {
		this.id = id;
		this.descricao = descricao;
		this.id_categoria = id_categoria;
		this.marca = marca;
		this.url_foto = url_foto;
		this.valor = valor;
		this.detalhes = detalhes;
		this.nome_categoria = nome_categoria;
	}
	
	//Construtor vazio
	public Brinquedo() {
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getUrl_foto() {
		return url_foto;
	}
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = detalhes;
	}
		
}
