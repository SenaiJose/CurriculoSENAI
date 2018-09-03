package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.Curriculo;
import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;

public class CurriculoDao {
	
	private Connection conn;
	
	public CurriculoDao() {
		conn = ConnectionDB.getConnection();
	}
	
	public List<Curriculo> listarCurriculo (String id_usuario) throws SQLException {
		String sql = "SELECT curri.data_criacao, tur.nome AS nomeTurma, cur.nome AS nomeCurso, curri.semestre FROM curriculum_vitae AS curri"
				+ "INNER JOIN turma AS tur ON tur.id = curri.id_turma "
				+ "INNER JOIN curso AS cur ON cur.id = curri.id_curso "
				+ "WHERE curri.id_usuario = 1;" ;
				
				
				 
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id_usuario);
		
		ResultSet rs = ps.executeQuery();
		List<Curriculo> lista = new ArrayList<Curriculo>();
		while(rs.next()) {
			Curriculo c = new Curriculo();
			
			c.getTurma().setNome(rs.getString("nomeTurma"));
			c.getCurso().setNome(rs.getString("nomeCurso"));
			c.setData_criacao(rs.getInt("data_criacao"));
		}
		
		return lista;
		
	}

}
