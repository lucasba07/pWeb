package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.edu.ifgoiano.entidade.Atividades;
import br.edu.ifgoano.repositorio.AtividadesRepositorio;

@WebServlet("/deslogar")
public class DeslogarServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			
			AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
			Atividades atividades = new Atividades();
			atividades.setUsuario_id((Integer) session.getAttribute("usuarioId"));
			atividades.setAcao("logout");
			atividadeRepositorio.insertAtividades(atividades);
			
			session.invalidate();
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}