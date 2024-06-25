package br.edu.ifgoano.repositorio;

import java.sql.*;
import java.util.*;
import br.edu.ifgoiano.entidade.Usuario;

public class UsuarioRepositorio {
    private Connection conn;

    public UsuarioRepositorio() throws SQLException {
    	this.conn = DriverManager.getConnection(
    			"jdbc:h2:~/test;DB_CLOSE_DELAY=-1;AUTO_RECONNECT=TRUE;DATABASE_TO_UPPER=false", "sa", "sa");
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lstUsuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email, senha FROM usuario";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet resultSet = pst.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setNome(resultSet.getString("nome"));
                lstUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstUsuarios;
    }

    public Usuario getUsuarioById(int id) {
		String sql = "select id, nome, email from usuario where id = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, id);
			
			ResultSet resultSet = pst.executeQuery();
			
			if (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(id);
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				
				return usuario;
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta de usuários");
			e.printStackTrace();
		}
		
		throw new RuntimeException("Usuário não encontrado!");
	}
	
	public Usuario getUsuarioByEmail(String email) {
		String sql = "select id, nome, email, senha from usuario where email = ?";
		Usuario usuario = new Usuario();
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			
			ResultSet resultSet = pst.executeQuery();
			
			if (resultSet.next()) {
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(email);
				usuario.setSenha(resultSet.getString("senha"));
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta de usuários");
			e.printStackTrace();
		}
		
		return usuario;
	}
    
    public void adicionarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.executeUpdate();
        }
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.setInt(4, usuario.getId());
            pst.executeUpdate();
        }
    }

    public boolean deletarUsuario(int id) throws SQLException {
        String deleteUsuarioSql = "DELETE FROM usuario WHERE id = ?";
        try (PreparedStatement pstUsuario = conn.prepareStatement(deleteUsuarioSql)){
            pstUsuario.setInt(1, id);
            int linhasAfetadas = pstUsuario.executeUpdate();
            return linhasAfetadas > 0;
        }
    }


    public List<Usuario> pesquisarUsuarios(String termo) {
        List<Usuario> lstUsuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email, senha FROM usuario WHERE nome LIKE ? OR email LIKE ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + termo + "%");
            pst.setString(2, "%" + termo + "%");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setNome(resultSet.getString("nome"));
                lstUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstUsuarios;
    }
  
    public void fecharConexao() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
