package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifgoano.repositorio.AtividadesRepositorio;
import br.edu.ifgoano.repositorio.UsuarioRepositorio;
import br.edu.ifgoiano.entidade.Atividades;
import br.edu.ifgoiano.entidade.Usuario;

@WebServlet("/loginUsuario")
public class LoginUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginUsuarioServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String senha = request.getParameter("password");
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        boolean existeUsuario = false;
        
        if(email.isBlank() || senha.isBlank()) {
        	request.setAttribute("mostrarAlertaErro", Boolean.TRUE);
	        request.getRequestDispatcher("login.jsp").forward(request, response);
        }else {
        	 try {
        		 UsuarioRepositorio repositorio = new UsuarioRepositorio();
        		 Usuario usuario = new Usuario();
     			 usuario = repositorio.getUsuarioByEmail(email);
     			
        	      List<Usuario> users = repositorio.listarUsuarios();
                 for(Usuario user : users) {
                	 if(user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                		 existeUsuario = true;
                		 break;
                	 }
                 }
                 
                 if(existeUsuario) {
                	 HttpSession session = request.getSession();
                     session.setAttribute("email", email);
                     
                    AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
     				Atividades atividades = new Atividades();
     				atividades.setUsuario_id(repositorio.getUsuarioByEmail(request.getParameter("email")).getId());
     				atividades.setAcao("login");
     				atividadeRepositorio.insertAtividades(atividades);
     				
     				session.setAttribute("usuario", usuario);
     				session.setAttribute("usuarioId", repositorio.getUsuarioByEmail(request.getParameter("email")).getId());

                     
                	 response.sendRedirect("listagemUsuario");
                 }else {
                	 request.setAttribute("valoresInvalidos", Boolean.TRUE);
                	 request.getRequestDispatcher("login.jsp").forward(request, response);
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
       }   
	}
}