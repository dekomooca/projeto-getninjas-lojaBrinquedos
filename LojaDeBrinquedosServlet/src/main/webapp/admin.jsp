<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
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
	
    <title>Admin - Loja de Brinquedos</title>
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
					<a href="catalogo.jsp"> <span>Catálogo de Brinquedos</span></a>
				</li>
				<li>
					<a href="ServletLogin?cmd=admin"><span class="side-btns">Administração</span></a>
				</li>
				<li>
					<a href="equipe.jsp"> <span>Sobre a Equipe</span></a>
				</li>
			</ul>
		</div>

        <div class="content" id="form-login">
        
          <div class="container-fluid">
			<div id="title">
				<div class="breadcrumb-admin">
					<div class="container-breadcrumb">
						<nav aria-label="breadcrumb">
						  <ol class="breadcrumb breadcrumb2">
						    <li class="breadcrumb-item active" aria-current="page">Loja</li>
						    <li class="breadcrumb-item active" aria-current="page">Administração</li>
						  </ol>
						</nav>
					</div>
					<div class="container-btnSair">
						<a class="btn" id="btnSair" href="ServletLogin?cmd=logout">Logout</a>
					</div>
				</div>
			</div>
          </div>
          
        	<%
				int qtdBrinquedos = (Integer) request.getAttribute("qtdBrinquedos");
        		int qtdCategorias = (Integer) request.getAttribute("qtdCategorias");
        		int qtdUsuarios = 	(Integer) request.getAttribute("qtdUsuarios");
			%>
         <div class="dashBoard">
     		<div class="cards">
                <div class="card text-black mb-3">
				  <h5 class="card-header">Brinquedos</h5>
				  <div class="card-body">
				    <h5 class="card-title">Total: <%=qtdBrinquedos%></h5>
				    <a href="ServletBrinquedo?cmd=listar" class="btn btn-primary">Gerenciar</a>
				  </div>
				</div>
				
                <div class="card text-black mb-3">
				  <h5 class="card-header">Categorias</h5>
				  <div class="card-body">
				    <h5 class="card-title">Total: <%=qtdCategorias%></h5>
				    <a href="ServletCategoria?cmd=listar" class="btn btn-primary">Gerenciar</a>
				  </div>
           		</div>
           		
         		<div class="card text-black mb-3">
				  <h5 class="card-header">Usuários</h5>
				  <div class="card-body">
				    <h5 class="card-title">Total: <%=qtdUsuarios%></h5>
				    <a href="ServletUser?cmd=listar" class="btn btn-primary">Gerenciar</a>
				  </div>
           		</div>
          	</div>
         </div>
        </div>
        </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>