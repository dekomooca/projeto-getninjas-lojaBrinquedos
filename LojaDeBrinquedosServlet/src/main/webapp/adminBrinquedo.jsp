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
						    <li class="breadcrumb-item" aria-current="page"><a href="ServletLogin?cmd=admin">Administração</a></li>
						    <li class="breadcrumb-item active" aria-current="page">Brinquedos</li>
						  </ol>
						</nav>
					</div>
					<div class="container-btnSair">
						<a class="btn" id="btnSair" href="ServletLogin?cmd=logout">Logout</a>
					</div>
				</div>
			</div>
          </div>
          
		          <!-- Modal de Confirmação -->
		<div class="modal fade" id="confirmacaoExclusaoModal" tabindex="-1" role="dialog" aria-labelledby="confirmacaoExclusaoModalLabel" aria-hidden="true">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="confirmacaoExclusaoModalLabel">Exclusão de Produto</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                </button>
		            </div>
		            <div class="modal-body">
		                Confirmar a exclusão do produto escolhido?
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-success" data-dismiss="modal">Cancelar</button>
		                <button type="button" class="btn btn-danger" id="btn-confirmar-exclusao">Confirmar</button>
		            </div>
		        </div>
		    </div>
		</div>
          
        <a href="ServletBrinquedo?cmd=novo"><button class="btn" id="btn-novo-brinquedo">Novo Brinquedo</button></a>
          <div class="containerTabelaAdmin">
          <table class="table-custom table-striped" id="tabela-brinquedos">
            <thead class="thead-color">
              <tr>
                <th scope="col">Cod</th>
                <th scope="col">Descrição</th>
                <th scope="col">Categoria</th>
                <th scope="col">Valor</th>
                <th scope="col">Controles</th>
              </tr>
            </thead>
            <tbody>
              <tr>
					<%
					List<Brinquedo> lista = (List<Brinquedo>) request.getAttribute("brinquedosList");
					if (lista != null) {
					    for (Brinquedo a : lista) {
					%>
					<tr id="brinquedoId<%=a.getId()%>">
					    <td><%=a.getId()%></td>
					    <td><%=a.getDescricao()%></td>
					    <td><%=a.getNome_categoria()%></td>
					    <td>R$ <%=String.format("%.2f", a.getValor())%></td>
					    <td><button class="btn" id="btn-editar" onclick="btnEditarBrinquedo(this)">Editar</button><button class="btn" id="btn-excluir" onclick="btnExcluirBrinquedo(this)">Excluir</button></td>
					</tr>
					<%
					    }
					} else {
					    // Lide com a lista de brinquedos nula de acordo com a lógica do seu aplicativo.
						System.out.println("A lista de brinquedos enviada para o JSP está nula.");
					}
					%>
            </tbody>
          </table>
		</div>
      </div>
	</div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>