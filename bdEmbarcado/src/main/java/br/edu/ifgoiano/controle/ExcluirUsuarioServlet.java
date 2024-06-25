package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifgoano.repositorio.AtividadesRepositorio;
import br.edu.ifgoano.repositorio.UsuarioRepositorio;
import br.edu.ifgoiano.entidade.Atividades;

@WebServlet("/delete")
public class ExcluirUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UsuarioRepositorio repositorio = null;
        
        try {
            repositorio = new UsuarioRepositorio();
            boolean deletado = repositorio.deletarUsuario(id);
            
            if (deletado) {
            	HttpSession session = request.getSession(false);
    			
    			AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
    			Atividades atividades = new Atividades();
    			atividades.setUsuario_id((Integer) session.getAttribute("usuarioId"));
    			atividades.setAcao("exclusão");
    			atividadeRepositorio.insertAtividades(atividades);
    			
                response.sendRedirect("listagemUsuario");
            } else {
                response.getWriter().println("Não foi possível excluir o usuário.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao excluir o usuário: " + e.getMessage());
        } finally {
            if (repositorio != null) {
                try {
                    repositorio.fecharConexao();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
