package br.edu.ifgoiano.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifgoiano.entidade.Atividades;
import br.edu.ifgoiano.entidade.Usuario;
import br.edu.ifgoano.repositorio.AtividadesRepositorio;
import br.edu.ifgoano.repositorio.UsuarioRepositorio;

@WebServlet("/atualizar")
public class AtualizarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		 request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
        
		 int id = Integer.parseInt(request.getParameter("id"));
		 String nome = request.getParameter("nome");
         String email = request.getParameter("email");
         String senha = request.getParameter("senha");

         Usuario usuario = new Usuario();
         usuario.setId(id);
         usuario.setNome(nome);
         usuario.setEmail(email);
         usuario.setSenha(senha);
         
         if(nome.isBlank() || email.isBlank() || senha.isBlank()) {
         	request.setAttribute("mostrarAlertaErro", Boolean.TRUE);
 	        request.getRequestDispatcher("atualizarUsuario.jsp").forward(request, response);
         } else {
        	 try {
     	    	UsuarioRepositorio repositorio = new UsuarioRepositorio();
     	        repositorio.atualizarUsuario(usuario);
     	        
     	        HttpSession session = request.getSession(false);

				AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
				Atividades atividades = new Atividades();
				atividades.setUsuario_id((Integer) session.getAttribute("usuarioId"));
				atividades.setAcao("atualização");
				atividadeRepositorio.insertAtividades(atividades);
     	        
     		    response.sendRedirect("listagemUsuario");   
     	    } catch (Exception e) {
     	        e.printStackTrace();
     	    }
     	}
      }
}
