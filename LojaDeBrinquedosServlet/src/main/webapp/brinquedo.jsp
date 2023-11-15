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

<title>Loja - Brinquedo</title>
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
		
		
		<jsp:useBean id="brinquedo" scope="session" class="br.edu.unicid.model.Brinquedo" />
		
        <div class="content">
          <div class="container-fluid">
        		<div id="title">
					<nav aria-label="breadcrumb">
					  <ol class="breadcrumb">
					    <li class="breadcrumb-item"><a href="catalogo.jsp">Catálogo de Brinquedos</a></li>
					    <li class="breadcrumb-item"><a href="ServletCategoria?cmd=filtrar&txtCodigo=<%=brinquedo.getId_categoria()%>&txtNome=<%=brinquedo.getNome_categoria()%>"><%=brinquedo.getNome_categoria()%></a></li>
					    <li class="breadcrumb-item active" aria-current="page"><%=brinquedo.getDescricao()%></li>
					  </ol>
					</nav>
				</div>
            </div>
          
          <div class="product-container">
            <div class="product-details">
                <img class="product-img" src="<%=brinquedo.getUrl_foto()%>" alt="">
                <div class="product-descri">
                    <span id="product-cod">Cod.: <%=brinquedo.getId()%></span>
                    <span id="product-descri"><%=brinquedo.getDescricao()%></span>
                    <span id="product-price">R$ <%=String.format("%.2f", brinquedo.getValor())%></span>
                </div>
            </div>
            <div class="more-about">
                <span id="title-about"><u>Detalhes sobre o produto:</u></span>
                <span id="text-about"><%=brinquedo.getDetalhes()%></span>
            </div>
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