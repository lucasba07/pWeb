package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/cadastroUsuario")
public class CadastroUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    	Usuario usuario = new Usuario();
        
    	String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        
        
        if(nome.isBlank() || email.isBlank() || senha.isBlank()) {
        	request.setAttribute("mostrarAlertaErro", Boolean.TRUE);
	        request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
             try {
                 UsuarioRepositorio repositorio = new UsuarioRepositorio();
                 List<Usuario> users = repositorio.listarUsuarios();
                 Boolean existeUsuario = false;
                 
                 for(Usuario user : users) {
                	 if(user.getEmail().equals(email)) {
                		 existeUsuario = true;
                		 break;
                	 }
                 }
                 
                 if(!existeUsuario) {
                	 repositorio.adicionarUsuario(usuario);
                	 
                	AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
 					Atividades atividades = new Atividades();
 					atividades.setUsuario_id(repositorio.getUsuarioByEmail(request.getParameter("email")).getId());
 					atividades.setAcao("cadastro");
 					atividadeRepositorio.insertAtividades(atividades);
 					
 					request.setAttribute("usuarios", repositorio.listarUsuarios());
 					HttpSession session = request.getSession(false);
 					session.setAttribute("usuario", usuario);
 					session.setAttribute("usuarioId", repositorio.getUsuarioByEmail(request.getParameter("email")).getId());
                	 
                	 request.getRequestDispatcher("login.jsp").forward(request, response);
                 }else {
                	 request.setAttribute("jaExiste", Boolean.TRUE);
                	 request.getRequestDispatcher("index.jsp").forward(request, response);
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }
}
