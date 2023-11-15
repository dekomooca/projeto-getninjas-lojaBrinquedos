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
    
    <title>Admin - Novo Brinquedo</title>
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
		
		<jsp:useBean id="brinquedo" scope="session" class="br.edu.unicid.model.Brinquedo" />
        <div class="content" id="form-login">
          <div class="container-fluid">
			<div id="title">
				<div class="breadcrumb-admin">
					<div class="container-breadcrumb">
						<nav aria-label="breadcrumb">
						  <ol class="breadcrumb">
						  	<li class="breadcrumb-item" aria-current="page"><a href="ServletLogin?cmd=admin">Administração</a></li>
						    <li class="breadcrumb-item" aria-current="page"><a href="ServletBrinquedo?cmd=listar">Brinquedos</a></li>
						    <li class="breadcrumb-item active" aria-current="page">Novo Brinquedo</li>
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
				int ultimoBrinquedo = (Integer) request.getAttribute("brinquedoId");
			%>
          <div class="container-large">
	          <form class="form-product" action="ServletBrinquedo" method="post" enctype="multipart/form-data">
				<input type="hidden" name="cmd" value="incluir" />
	            <div class="form-group row">
	              <label for="txtCodigo" class="col-sm-2 col-form-label">Código:</label>
	              <div class="col-sm-4">
	                <input type="number" class="form-control" name="txtCodigo" min="<%=ultimoBrinquedo%>" value="<%=ultimoBrinquedo%>" required/>
	              </div>
	            </div>
	
	            <div class="form-group row">
	              <label for="txtDescricao" class="col-sm-2 col-form-label">Descrição:</label>
	              <div class="col-sm-auto">
	                <input type="text" class="form-control" name="txtDescricao" placeholder="Descrição do Produto" required/>
	              </div>
	            </div>
	            
	            <div class="form-group row">
	                <label for="txtCategoria" class="col-sm-2 col-form-label">Categorias:</label>
	                <div class="col-sm-auto">
	                <select class="custom-select" id="selectCategorias"  name="txtCategoria" >
	                <%
					CategoriaDAO dao = new CategoriaDAO();
	
					List<Categoria> lista = dao.TodasCategorias();
					if (lista != null) {
						for (Categoria a : lista) {
					%>
						<option value="<%=a.getId()%>"><%=a.getNome()%></option>
					<%
					}
					} else {
					// Lide com a lista de brinquedos nula de acordo com a lógica do seu aplicativo.
					System.out.println("A lista de categorias enviada para o JSP está nula.");
					}
					%>                
	                  
	                  </select>
	                </div>
	            </div>
	
	            <div class="form-group row">
	                <label for="txtMarca" class="col-sm-2 col-form-label">Marca:</label>
	                <div class="col-sm-auto">
	                  <input type="text" class="form-control" name="txtMarca" placeholder="Marca do Produto" required/>
	                </div>
	            </div>
	       
	            <div class="form-group row">
	                <label for="txtValor" class="col-sm-2 col-form-label">Valor:</label>
	                <div class="col-sm-4">
	                  <input type="number" step="0.01" class="form-control" name="txtValor" placeholder="0,00" required/>
	                </div>
	            </div>
	
	            <div class="form-group row">
	                <label for="txtUrl_foto" class="col-sm-2 col-form-label">Imagem:</label>
	                <div class="col-sm-auto">
	                  <input type="file" class="form-control" id="file" name="file" accept="image/*" onchange="validateFileType()" required/>
	                  <small class="text-muted">Apenas arquivos jpg, jpeg, png, svg e bmp são aceitos!</small>
	                </div>
	            </div>
	
	            <div class="form-group row">
	                <label for="txtDetalhes" class="col-sm-2 col-form-label">Detalhes:</label>
	                <div class="col-md">
	                  <textarea class="form-control" name="txtDetalhes" rows="3" required></textarea>
	                </div>
	            </div>
	
	            <div class="btn-form">
	                <button type="submit" class="btn" id="btn-salvar">Cadastrar</button>
	                <button type="reset" class="btn" id="btn-limpar">Limpar</button>
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