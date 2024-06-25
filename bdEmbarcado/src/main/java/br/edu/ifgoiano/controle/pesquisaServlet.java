package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifgoiano.entidade.Usuario;
import br.edu.ifgoano.repositorio.UsuarioRepositorio;

@WebServlet("/pesquisar")
public class pesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public pesquisaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String termo = request.getParameter("termo");
	    try {
	        UsuarioRepositorio repositorio = new UsuarioRepositorio();
	        List<Usuario> lstUsuario = repositorio.pesquisarUsuarios(termo);
	        request.setAttribute("usuarios", lstUsuario);
	        request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
