package br.edu.ifgoiano.controle;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		final String email = req.getParameter("email");
		final String password = req.getParameter("password");
		
		System.out.println("O email digitado foi: " .concat(email));
		System.out.println("A senha digitada foi: " .concat(password));
	}
}
