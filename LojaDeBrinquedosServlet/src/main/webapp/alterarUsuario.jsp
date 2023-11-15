<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="br.edu.unicid.model.User"%>
<!doctype html>
<html lang="pt-br">
  <head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


	<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script async src="script.js"></script>	
    
    <title>Admin - Editar Usuário</title>
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
        
        <jsp:useBean id="usuario" scope="session" class="br.edu.unicid.model.User" />

        <div class="content" id="form-login">
          <div class="container-fluid">
			<div id="title">
				<div class="breadcrumb-admin">
					<div class="container-breadcrumb">
						<nav aria-label="breadcrumb">
						  <ol class="breadcrumb breadcrumb2">
						  	<li class="breadcrumb-item" aria-current="page"><a href="ServletLogin?cmd=admin">Administração</a></li>
						    <li class="breadcrumb-item" aria-current="page"><a href="ServletUser?cmd=listar">Usuários</a></li>
						    <li class="breadcrumb-item active" aria-current="page">Editar Usuário</li>
						  </ol>
						</nav>
					</div>
					<div class="container-btnSair">
						<a class="btn" id="btnSair" href="ServletLogin?cmd=logout">Logout</a>
					</div>
				</div>
			</div>
          </div>
         <div class="container-large"> 
			<form class="form-product" action="ServletUser?cmd=atualizar" method="post">
	            <div class="form-group row">
	              <label for="txtId" class="col-sm-2 col-form-label">Código:</label>
	              <div class="col-sm-4">
	                <input type="number" class="form-control" name="txtId" value="<%=usuario.getId()%>" readonly="readonly">
	              </div>
	            </div>
	
	            <div class="form-group row">
	              <label for="txtLogin" class="col-sm-2 col-form-label">Login:</label>
	              <div class="col-sm-auto">
	                <input type="text" class="form-control" name="txtLogin" value="<%=usuario.getLogin()%>">
	              </div>
	            </div>
	
	            <div class="form-group row">
	              <label for="txtPassword" class="col-sm-2 col-form-label">Senha:</label>
	              <div class="col-sm-auto">
	                <input type="password" class="form-control" name="txtPassword" value="<%=usuario.getPassword()%>">
	              </div>
	            </div>
	
	            <div class="btn-form">
	                <button type="submit" class="btn" id="btn-salvar">Atualizar Dados</button>
	            </div>
	    
	         </form>

        </div>
      </div>
	</div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>