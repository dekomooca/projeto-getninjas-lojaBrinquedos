package br.edu.unicid.model;

public class Categoria {
	private int id;
	private String nome;
	private String url_foto;
	
	//Metodo Construtor completo
	public Categoria(int id, String nome, String url_foto) {
		this.id = id;
		this.nome = nome;
		this.url_foto = url_foto;
	}
	
	//Metodo Construtor Simples
	public Categoria(String nome, String url_foto) {
		this.nome = nome;
		this.url_foto = url_foto;
	}
	
	//Metodo Vazio
	public Categoria() {
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrl_foto() {
		return url_foto;
	}
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
		
}
