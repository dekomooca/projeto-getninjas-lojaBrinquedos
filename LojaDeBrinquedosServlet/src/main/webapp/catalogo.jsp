<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="br.edu.unicid.dao.CategoriaDAO"%>
<%@ page import="br.edu.unicid.model.Categoria"%>
<!doctype html>
<html lang="pt-br">
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


	<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script async src="script.js"></script>	

<title>Loja - Catalogo de Brinquedos</title>
</head>
<body>
	<nav class="navbar navbar-light">
		<a class="navbar-brand" href="index.jsp">
		<img src="RES/logo.svg" width="30" height="30" class="d-inline-block align-top" alt=""><span id="brand">Loja de Brinquedos</span>
		</a>
	</nav>

	<div class="viewport">

		<div class="sidebar">
			<header>
				<span>Menu Principal</span>
			</header>
			<ul class="nav">
				<li>
					<a href="index.jsp"><span>Home</span></a>
				</li>
				<li>
					<a href="catalogo.jsp"><span>Catálogo de Brinquedos</span></a>
				</li>
				<li>
					<a href="ServletLogin?cmd=admin"><span class="side-btns">Administração</span></a>
				</li>
				<li>
					<a href="equipe.jsp"> <span>Sobre a Equipe</span></a>
				</li>
			</ul>
		</div>

		<div class="content">
			<div class="container-fluid">
				<div id="title">
					<nav aria-label="breadcrumb">
					  <ol class="breadcrumb">
					    <li class="breadcrumb-item active">Catálogo de Brinquedos</li>
					    <li class="breadcrumb-item active" aria-current="page">Categorias</li>
					  </ol>
					</nav>
				</div>
			</div>
			<div class="container-cards">

				<%
				CategoriaDAO dao = new CategoriaDAO();

				List<Categoria> lista = dao.TodasCategorias();
				if (lista != null) {
					for (Categoria a : lista) {
				%>
				<div class="card" style="width: 18rem;">
					<a href="ServletCategoria?cmd=filtrar&txtCodigo=<%=a.getId()%>&txtNome=<%=a.getNome()%>">
						<span id="categoria" onclick="categoria(this)"><img class="card-img-top" src="<%=a.getUrl_foto()%>" width="80px" height="80px" alt="Card image cap"/>
							<div class="card-body">
								<p class="card-text"><%=a.getNome()%></p>
							</div>
						</span>
					</a>
				</div>
				<%
				}
				} else {
				// Lide com a lista de brinquedos nula de acordo com a lógica do seu aplicativo.
				System.out.println("A lista de categorias enviada para o JSP está nula.");
				}
				%>
			</div>
		</div>

	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>