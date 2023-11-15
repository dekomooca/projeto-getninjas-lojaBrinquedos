<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="br.edu.unicid.dao.BrinquedoDAO"%>
<%@ page import="br.edu.unicid.model.Brinquedo"%>
<!doctype html>
<html lang="pt-br">
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


	<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script async src="script.js"></script>	

<title>Loja - Categoria</title>
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
		
		<jsp:useBean id="categoria" scope="session" class="br.edu.unicid.model.Categoria" />
		<div class="content">
			<div class="container-fluid">

				<%
				String categoria2 = (String) request.getAttribute("categoria");
				%>

				<div id="title">
					<nav aria-label="breadcrumb">
					  <ol class="breadcrumb">
					    <li class="breadcrumb-item"><a href="catalogo.jsp">Catálogo de Brinquedos</a></li>
					    <li class="breadcrumb-item active" aria-current="page"><%=categoria2%></li>
					  </ol>
					</nav>
				</div>
			</div>
			<div class="container-cards">

				<%
				BrinquedoDAO dao = new BrinquedoDAO();

				List<Brinquedo> lista = (List<Brinquedo>) request.getAttribute("brinquedosList");
				if (lista != null) {
				    for (Brinquedo a : lista) {
				%>
				<div class="card" style="width: 18rem;">
					<a id="produto" href="ServletBrinquedo?cmd=mostrar&txtCodigo=<%=a.getId()%>">
						<span><img class="card-img-top" src="<%=a.getUrl_foto()%>" width="80px" height="80px" alt="Card image cap"/>
							<div class="card-body">
								<p class="card-text"><%=a.getDescricao()%></p>
								<h5 class="card-title">R$ <%=String.format("%.2f", a.getValor())%></h5>
							</div>
						</span>
					</a>
				</div>
				<%
				}
					
				} else {
				// Lide com a lista de brinquedos nula de acordo com a lógica do seu aplicativo.
				System.out.println("A lista de brinquedos enviada para o JSP está nula.");
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