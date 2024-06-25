package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifgoano.repositorio.AtividadesRepositorio;
import br.edu.ifgoiano.entidade.Atividades;

@WebServlet("/listagemAtividades")
public class ListagemAtividadesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if(session != null && (String) session.getAttribute("email") != null) {
            try {
                AtividadesRepositorio repositorio = new AtividadesRepositorio();
                List<Atividades> lstAtividades = repositorio.listAtividades();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                for (Atividades atividade : lstAtividades) {
                    String dataFormatada = sdf.format(atividade.getData_hora());
                    atividade.setData_hora_formatada(dataFormatada);
                }
                
                request.setAttribute("atividades", lstAtividades);
                request.getRequestDispatcher("registroAtividades.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("erroCadastro", Boolean.TRUE);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
