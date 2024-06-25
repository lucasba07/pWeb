package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifgoano.repositorio.UsuarioRepositorio;
import br.edu.ifgoiano.entidade.Usuario;

@WebServlet("/listagemUsuario")
public class ListagemUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    	
    	if(session != null && (String) session.getAttribute("email") != null) {
    		try {
                UsuarioRepositorio repositorio = new UsuarioRepositorio();
                List<Usuario> lstUsuario = repositorio.listarUsuarios();
                request.setAttribute("usuarios", lstUsuario);
                request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	} else {
    		request.setAttribute("erroCadastro", Boolean.TRUE);
    		request.getRequestDispatcher("index.jsp").forward(request, response);
    	}
    }
}
