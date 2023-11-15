package br.edu.unicid.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unicid.dao.BrinquedoDAO;
import br.edu.unicid.dao.CategoriaDAO;
import br.edu.unicid.dao.UserDAO;
import br.edu.unicid.model.User;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Define o Charset para UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lógica
        String cmd = request.getParameter("cmd");
        UserDAO dao;
        BrinquedoDAO dao2;
        CategoriaDAO dao3;
        User user = new User();

        if (cmd != null) {
            try {
                user.setId(Integer.parseInt(request.getParameter("txtId")));
                user.setLogin(request.getParameter("txtLogin"));
                user.setPassword(request.getParameter("txtPassword"));

            } catch (Exception ex) {
                System.out.println("ALERTA: Problema ao setar valores " + ex.getMessage() + " no objeto usuario");
            }
            try {
                // Direciona para página JSP
                javax.servlet.RequestDispatcher rd = null;

                switch (cmd) {
                    case "admin":
                        HttpSession session = request.getSession(false);
                        // Verifica se o usuário está logado
                        if (session.getAttribute("usuarioLogado") == null) {
                            // Se o valor de sessão usuarioLogado é nulo, retorna para página de login
                            rd = request.getRequestDispatcher("login.jsp");
                        } else {
                            if ((boolean) session.getAttribute("usuarioLogado")) {
                                // Se o valor de sessão usuarioLogado é true, segue a lógica
                            	dao = new UserDAO();
                                dao2 = new BrinquedoDAO();
                                dao3 = new CategoriaDAO();
                                
                                request.setAttribute("qtdUsuarios", dao.TotalUsuarios());
                        		request.setAttribute("qtdBrinquedos", dao2.TotalBrinquedos());
                        		request.setAttribute("qtdCategorias", dao3.TotalCategorias());
                                rd = request.getRequestDispatcher("admin.jsp");
                            } else {
                                // Se não for nulo e diferente de true, segue a lógica
                                // Seta a tentativa de login inválido como inválida
                                request.setAttribute("loginInvalido", "invalida");
                                rd = request.getRequestDispatcher("login.jsp");
                            }
                        }
                        break;
                    case "login":
                        dao = new UserDAO();
                        dao2 = new BrinquedoDAO();
                        dao3 = new CategoriaDAO();
                        
                        String login = request.getParameter("txtLogin");
                        String password = request.getParameter("txtPassword");
                        
                        User user1 = dao.autentica(login, password);
                        
                        if (user1 != null) {
                        	request.setAttribute("qtdUsuarios", dao.TotalUsuarios());
                    		request.setAttribute("qtdBrinquedos", dao2.TotalBrinquedos());
                    		request.setAttribute("qtdCategorias", dao3.TotalCategorias());
                    		rd = request.getRequestDispatcher("admin.jsp");
                            HttpSession session1 = request.getSession();
                            session1.setAttribute("usuarioLogado", true);
                        } else {
                            // Falha no login
                            // Seta a tentativa de login inválido como inválida
                            request.setAttribute("loginInvalido", "invalida");
                            rd = request.getRequestDispatcher("login.jsp");
                        }
                        break;
                    case "logout":
                        HttpSession sessionLogout = request.getSession(false);

                        if (sessionLogout != null) {
                            sessionLogout.invalidate();
                            rd = request.getRequestDispatcher("index.jsp");
                        }
                        break;
                }

                // Executa a ação de direcionar para a página JSP
                if (rd != null) {
                    rd.forward(request, response);
                }

            } catch (Exception ex) {
                System.out.println("ERRO: Comando Inválido ou não foi possível criar um novo objeto usuário " + ex.toString());
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
