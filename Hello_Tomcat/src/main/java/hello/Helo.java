package hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Helo")
public class Helo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public Helo() {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		
		System.out.println("O email digitado foi: " + email);
		System.out.println("A senha digitada foi: " + senha);
		request.getRequestDispatcher("Hello.jsp").forward(request, response);
	}
	
}
