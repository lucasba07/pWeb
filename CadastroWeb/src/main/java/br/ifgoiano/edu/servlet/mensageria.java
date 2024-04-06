package br.ifgoiano.edu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifgoiano.edu.usuarios.Usuarios;

@WebServlet("/mensageria")
public class mensageria extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public mensageria() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        enviarListaDeUsuarios(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	String texto = request.getParameter("text");
        String email = request.getParameter("email");
        String titulo = request.getParameter("title");
        String cumprimento = request.getParameter("cumprimento");
        boolean autoEmail = request.getParameter("autoEmail") != null;

        if (email == null || email.isBlank() || titulo == null || titulo.isBlank() || texto == null || texto.isBlank()) {
        	request.setAttribute("emailValue", email);
            request.setAttribute("tituloValue", titulo);
            request.setAttribute("textoValue", texto);
        	
        	request.setAttribute("incompleto", true);
            enviarListaDeUsuarios(request, response);
        } else {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("\n");
            mensagem.append("E-mail enviado para: ").append(email).append("\n");
            mensagem.append("\n");
            mensagem.append(titulo).append("\n");
            mensagem.append("\n");

            if (cumprimento != null) {
                if (cumprimento.equals("formal")) {
                    mensagem.append("Prezada(o),\n");
                } else if (cumprimento.equals("horario")) {
                    int hora = java.time.LocalTime.now().getHour();
                    if (hora >= 5 && hora < 12) {
                        mensagem.append("Bom dia,\n");
                    } else if (hora >= 12 && hora < 19) {
                        mensagem.append("Boa tarde,\n");
                    } else {
                        mensagem.append("Boa noite,\n");
                    }
                }
            }

            mensagem.append(texto).append("\n");

            if (autoEmail) {
                mensagem.append("\nAtenção: esse é um e-mail automático e não deve ser respondido.");
                mensagem.append("\n");
            }

            System.out.println("Simulando envio do e-mail...\n" + mensagem.toString());

            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
        }
    }


    private void enviarListaDeUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuarios> listaDeUsuarios = new ArrayList<Usuarios>();

        for (int i = 0; i < 10; i++) {
            Usuarios usuario = new Usuarios();
            usuario.setId(i);
            usuario.setName("Usuário " + i);
            usuario.setEmail("usuario" + i + "@gmail.com");

            listaDeUsuarios.add(usuario);
        }

        request.setAttribute("listaDeUsuarios", listaDeUsuarios);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
