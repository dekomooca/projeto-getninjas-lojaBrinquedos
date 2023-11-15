package br.edu.unicid.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.edu.unicid.dao.CategoriaDAO;
import br.edu.unicid.dao.BrinquedoDAO;
import br.edu.unicid.model.Categoria;
import br.edu.unicid.model.Brinquedo;

@WebServlet("/ServletCategoria")
@MultipartConfig
public class ServletCategoria extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Define o Charset para UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lógica
        String cmd = request.getParameter("cmd");
        CategoriaDAO dao;
        BrinquedoDAO dao1;
        Categoria categoria = new Categoria();

        if (cmd != null) {
            try {
                categoria.setId(Integer.parseInt(request.getParameter("txtCodigo")));
                categoria.setNome(request.getParameter("txtNome"));

            } catch (Exception ex) {
                System.out.println("ALERTA: Problema ao setar valores " + ex.getMessage() + " no objeto categoria");
            }

            try {
            	dao = new CategoriaDAO();
                // Direciona para página JSP
                javax.servlet.RequestDispatcher rd = null;

                switch (cmd) {
                    case "incluir":
                        HttpSession session = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (session.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) session.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                            	String uploadPath = getServletContext().getRealPath("/RES");
                            	Part filePart = request.getPart("file");
                            	String fileName = null ; //filePart.getSubmittedFileName();
                            	String filePath = uploadPath + File.separator + fileName;
                            	
                            	 try (FileOutputStream out = new FileOutputStream(filePath);
                                        InputStream fileContent = filePart.getInputStream()) {
                                        int read;
                                        final byte[] bytes = new byte[1024];
                                        while ((read = fileContent.read(bytes)) != -1) {
                                            out.write(bytes, 0, read);
                                        }
                            	 }
                            	categoria.setUrl_foto("RES\\" + fileName);
                                dao.Salvar(categoria);
                                rd = request.getRequestDispatcher("ServletCategoria?cmd=listar");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        
                        break;
                    case "listar":
                        HttpSession sessionListar = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (sessionListar.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) sessionListar.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                                List<Categoria> categoriasList = dao.TodasCategorias();
                                if (categoriasList != null) {
                                    request.setAttribute("categoriasList", categoriasList);
                                    rd = request.getRequestDispatcher("adminCategoria.jsp");
                                } else {
                                    System.out.println("ERRO: A lista de categorias geradas no Servlet está nula.");
                                }
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }                    	

                        break;
                    case "excluir":
                    	dao1 = new BrinquedoDAO();
                        HttpSession sessionExcluir = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (sessionExcluir.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) sessionExcluir.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                            	dao1.ExcluirPorCategoria(categoria.getId());
                                dao.Excluir(categoria);
                                rd = request.getRequestDispatcher("ServletCategoria?cmd=listar");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        
                        break;
                    case "atu":
                        session = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (session.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) session.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                                categoria = dao.ProcurarCategoria(categoria.getId());
                                javax.servlet.http.HttpSession sessionAtu = request.getSession(true);
                                sessionAtu.setAttribute("categoria", categoria);
                                rd = request.getRequestDispatcher("alterarCategoria.jsp");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        
                    	break;
                    case "atualizar":
                        session = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (session.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) session.getAttribute("usuarioLogado")) {
                                // Nao atualizou a foto
                            	if(request.getPart("file").getSize() == 0) {
                            		dao.AtualizarSemFoto(categoria);
                                	rd = request.getRequestDispatcher("ServletCategoria?cmd=listar");
                            	}
                            	// Atualizou a foto
                            	else {
                            		String uploadPath = getServletContext().getRealPath("/RES");
                                	Part filePart = request.getPart("file");
                                	String fileName = null ; //filePart.getSubmittedFileName();
                                	String filePath = uploadPath + File.separator + fileName;
                                	
                                	 try (FileOutputStream out = new FileOutputStream(filePath);
                                            InputStream fileContent = filePart.getInputStream()) {
                                            int read;
                                            final byte[] bytes = new byte[1024];
                                            while ((read = fileContent.read(bytes)) != -1) {
                                                out.write(bytes, 0, read);
                                            }               
                                	 }
                                categoria.setUrl_foto("RES\\" + fileName);
                                dao.AtualizarComFoto(categoria);
                                rd = request.getRequestDispatcher("ServletCategoria?cmd=listar");
                            	
                            	}
                            	
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        
                        break;
                    case "filtrar":
                        dao1 = new BrinquedoDAO();
                        List<Brinquedo> brinquedosList = dao1.BrinquedosCategoria(categoria.getId());
                        if (brinquedosList != null) {
                            request.setAttribute("brinquedosList", brinquedosList);
                            request.setAttribute("categoria", categoria.getNome());
                            rd = request.getRequestDispatcher("categoria.jsp");
                        } else {
                            System.out.println("ERRO: A lista de brinquedos por categoria geradas no Servlet está nula.");
                        }
                        
                        break;
                    case "nova":
                        HttpSession sessionNova = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (sessionNova.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) sessionNova.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                                categoria = dao.ultimaCategoria();
                                request.setAttribute("categoriaId", categoria.getId());
                                rd = request.getRequestDispatcher("novaCategoria.jsp");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        break;
                }

                // Executa a ação de direcionar para a página JSP
                if (rd != null) {
                    rd.forward(request, response);
                }

            } catch (Exception ex) {
                System.out.println("ERRO: Comando Inválido ou não foi possível criar um novo objeto categoria " + ex.toString());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
