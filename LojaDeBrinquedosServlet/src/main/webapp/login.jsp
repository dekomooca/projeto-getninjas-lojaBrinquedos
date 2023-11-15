<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
  <head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


	<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script async src="script.js"></script>		
    
    <title>Admin - Login</title>
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
		
				          <!-- Modal de Login invalido -->
		<div class="modal fade" id="loginInvalidoModal" tabindex="-1" role="dialog" aria-labelledby="loginInvalidoModalLabel" aria-hidden="true">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="loginInvalidoModalLabel">Login</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                </button>
		            </div>
		            <div class="modal-body">
		                Usuário e/ou senha inválidos !
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-success" data-dismiss="modal">Fechar</button>
		            </div>
		        </div>
		    </div>
		</div>
		
		
        <div class="content" id="form-login">
          <div class="container-fluid">
			<div id="title">
				<nav aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item active" aria-current="page">Administração</li>
				    <li class="breadcrumb-item active" aria-current="page">Login</li>
				  </ol>
				</nav>
			</div>
          </div>
          <div class="container-large">
          <form class="form-login" action="ServletLogin" method="post">
          	<img src="https://www.svgrepo.com/show/532323/lock-alt.svg" width="80px" height="80px">
			<input type="hidden" name="cmd" value="login" />
            <div class="form-group row">
              <label for="txtLogin" class="col-sm-2 col-form-label">Login: </label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="txtLogin" placeholder="Digite seu Login">
              </div>
            </div>

            <div class="form-group row">
                <label for="txtPassword" class="col-sm-2 col-form-label">Senha: </label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" name="txtPassword" placeholder="Digite sua Senha">
                </div>
            </div>
			
				<%
				String loginInvalido = (String) request.getAttribute("loginInvalido");
				%>
			
				<% if (loginInvalido == null) { %>
				    <div class="loginSpan"><span>Digite o login e a senha !</span></div>
				<% } else { %>
				    <div class="loginSpan"><span>Usuário e/ou senha inválidos !</span></div>
				<% } %>

            <div class="btn-form">
                <button type="submit" class="btn" id="btn-salvar">Login</button>
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