package br.edu.unicid.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unicid.dao.UserDAO;
import br.edu.unicid.model.User;

@WebServlet("/ServletUser")
public class ServletUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Define o Charset para UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lógica
        String cmd = request.getParameter("cmd");
        String txtId = request.getParameter("txtId");
        String txtLogin = request.getParameter("txtLogin");
        String txtPassword = request.getParameter("txtPassword");
        
        UserDAO dao;
        User user = new User();

        if (cmd != null) {
            try {
            	
                user.setLogin(txtLogin);
                user.setPassword(txtPassword);
                user.setId(Integer.parseInt(txtId));
                
            } catch (Exception ex) {
                System.out.println("ALERTA: Problema ao setar valores " + ex.getMessage() + " no objeto User");
            }

            try {
                dao = new UserDAO();
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
                                dao.Salvar(user);
                                rd = request.getRequestDispatcher("ServletUser?cmd=listar");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        break;
                   
                    case "listar":
                        List<User> userList = dao.TodosUsers();
                        if (userList != null) {
                            request.setAttribute("userList", userList);
                            rd = request.getRequestDispatcher("adminUsuario.jsp");
                            
                        } else {
                            System.out.println("ERRO: A lista de usuarios geradas no Servlet está nula.");
                        }
                        break;
                   
                    case "excluir":
                        dao.Excluir(user);
                        rd = request.getRequestDispatcher("ServletUser?cmd=listar");
                        break;
                   
                    case "atu":
                    	HttpSession session1 = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (session1.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) session1.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                                user = dao.ProcurarUser(user.getId());
                                HttpSession session2 = request.getSession(true);
                                session2.setAttribute("usuario", user);
                                rd = request.getRequestDispatcher("alterarUsuario.jsp");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        break;
                   
                    case "atualizar":
                    	HttpSession session2 = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (session2.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) session2.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                                dao.Atualizar(user);
                                rd = request.getRequestDispatcher("ServletUser?cmd=listar");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        break;  
                    	      
                    case "novo":
                        HttpSession session3 = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (session3.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para a página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) session3.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                                rd = request.getRequestDispatcher("novoUsuario.jsp");
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
                System.out.println("ERRO: Comando Inválido ou não foi possível criar um novo objeto User " + ex.toString());
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
