package br.edu.ifgoano.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifgoiano.entidade.Atividades;

public class AtividadesRepositorio {
	private Connection conn;
	
	public AtividadesRepositorio() throws SQLException{
		this.conn = DriverManager.getConnection(
				"jdbc:h2:~/test;DB_CLOSE_DELAY=-1;AUTO_RECONNECT=TRUE;DATABASE_TO_UPPER=false", "sa", "sa");

	}
	
	public List<Atividades> listAtividades(){
		List<Atividades> lstAtividades = new ArrayList<Atividades>();
		
		String sql = "select id, usuario_id, acao, data_hora from atividades";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet resultSet = pst.executeQuery();
			
			while (resultSet.next()) {
				Atividades atividades = new Atividades();
				atividades.setId(resultSet.getInt("id"));
				atividades.setUsuario_id(resultSet.getInt("usuario_id"));
				atividades.setAcao(resultSet.getString("acao"));
				atividades.setData_hora(resultSet.getTimestamp("data_hora"));
				
				lstAtividades.add(atividades);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na consulta de usu�rios");
		}
		
		return lstAtividades;
	}
	
	public void insertAtividades(Atividades atividades) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into atividades (usuario_id, acao) ");
		sql.append("values (?,?)");
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, atividades.getUsuario_id());
			pst.setString(2, atividades.getAcao());
			pst.execute();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na inclusão de atividades");
		}
	}
}