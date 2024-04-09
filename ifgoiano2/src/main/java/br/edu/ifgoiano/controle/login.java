package br.edu.ifgoiano.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public login() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        if (email.contains("@ifgoiano") && password.equals("123456")) {
            response.sendRedirect("https://getbootstrap.com/docs/5.3/examples/dashboard/");            
        } else if(email.isBlank() || password.isBlank() || email == null || password == null) {
            request.setAttribute("incompleto", true);
            request.setAttribute("passwordValue", password);
            request.setAttribute("emailValue", email);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("invalido", true);
            request.setAttribute("passwordValue", password);
            request.setAttribute("emailValue", email);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }     
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
