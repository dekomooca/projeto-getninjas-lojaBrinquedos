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

import br.edu.unicid.dao.BrinquedoDAO;
import br.edu.unicid.model.Brinquedo;

@WebServlet("/ServletBrinquedo")
@MultipartConfig
public class ServletBrinquedo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Define o Charset para UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lógica
        String cmd = request.getParameter("cmd");
        BrinquedoDAO dao;
        Brinquedo brinquedo = new Brinquedo();

        if (cmd == null) {
            return; // Retorna se cmd for nulo
        }

        try {
            brinquedo.setId(Integer.parseInt(request.getParameter("txtCodigo")));
            brinquedo.setDescricao(request.getParameter("txtDescricao"));
            brinquedo.setId_categoria(Integer.parseInt(request.getParameter("txtCategoria")));
            brinquedo.setMarca(request.getParameter("txtMarca"));
            brinquedo.setValor(Float.parseFloat(request.getParameter("txtValor")));
            brinquedo.setDetalhes(request.getParameter("txtDetalhes"));
        } catch (Exception ex) {
            System.out.println("ALERTA: Problema ao setar valores " + ex.getMessage() + " no objeto brinquedo");
        }

        try {
            dao = new BrinquedoDAO();
            // Direciona para a página JSP
            javax.servlet.RequestDispatcher rd = null;

            // Utiliza um switch para melhorar a legibilidade
            switch (cmd.toLowerCase()) {
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
                        	String fileName = null ; //filePart.getSubmittedFileName(filePart);
                        	String filePath = uploadPath + File.separator + fileName;
                        	
                        	 try (FileOutputStream out = new FileOutputStream(filePath);
                                    InputStream fileContent = filePart.getInputStream()) {
                                    int read;
                                    final byte[] bytes = new byte[1024];
                                    while ((read = fileContent.read(bytes)) != -1) {
                                        out.write(bytes, 0, read);
                                    }
                        	 }
                        	brinquedo.setUrl_foto("RES\\" + fileName);
                            dao.Salvar(brinquedo);
                            rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
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
                            List<Brinquedo> brinquedosList = dao.TodosBrinquedos();
                            if (brinquedosList != null) {
                                request.setAttribute("brinquedosList", brinquedosList);
                                rd = request.getRequestDispatcher("adminBrinquedo.jsp");
                            } else {
                                System.out.println("ERRO: A lista de brinquedos gerada no Servlet está nula.");
                            }
                        } else {
                            // Se não for nulo e diferente de true, segue a lógica
                            rd = request.getRequestDispatcher("login.jsp");
                        }
                    }
                    
                    break;

                case "excluir":
                    session = request.getSession(false);
                    // Verifica se o usuário está logado
                    if (session.getAttribute("usuarioLogado") == null) {
                        // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                        rd = request.getRequestDispatcher("login.jsp");
                    } else {
                        if ((boolean) session.getAttribute("usuarioLogado")) {
                            // Se o valor de sessão usuarioLogado é true, segue a lógica
                            dao.Excluir(brinquedo);
                            rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
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
                            brinquedo = dao.ProcurarBrinquedo(brinquedo.getId());
                            HttpSession session1 = request.getSession(true);
                            session1.setAttribute("brinquedo", brinquedo);
                            rd = request.getRequestDispatcher("alterarBrinquedo.jsp");
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
                        	// Não atualizou a foto
                        	if(request.getPart("file").getSize() == 0) {
                        		dao.AtualizarSemFoto(brinquedo);
                            	rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
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
                            	brinquedo.setUrl_foto("RES\\" + fileName);
                                dao.AtualizarComFoto(brinquedo);
                                rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
                        	}

                        } else {
                            // Se não for nulo e diferente de true, segue a lógica
                            rd = request.getRequestDispatcher("login.jsp");
                        }
                    }
                    
                    break;

                case "mostrar":
                    brinquedo = dao.ProcurarBrinquedo(brinquedo.getId());
                    session = request.getSession(true);
                    session.setAttribute("brinquedo", brinquedo);
                    rd = request.getRequestDispatcher("brinquedo.jsp");
                    break;

                case "novo":
                    session = request.getSession(false);
                    // Verifica se o usuário está logado
                    if (session.getAttribute("usuarioLogado") == null) {
                        // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                        rd = request.getRequestDispatcher("login.jsp");
                    } else {
                        if ((boolean) session.getAttribute("usuarioLogado")) {
                            // Se o valor de sessão usuarioLogado é true, segue a lógica
                            brinquedo = dao.ultimoBrinquedo();
                            request.setAttribute("brinquedoId", brinquedo.getId());
                            rd = request.getRequestDispatcher("novoBrinquedo.jsp");
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
            System.out.println("ERRO: Comando Inválido ou não foi possível criar um novo objeto" + ex.toString());
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
