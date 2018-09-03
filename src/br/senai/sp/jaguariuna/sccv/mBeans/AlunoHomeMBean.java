package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.senai.sp.jaguariuna.sccv.entities.Curriculo;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;

@ManagedBean
public class AlunoHomeMBean {
	
	CurriculoDao curriculoDao;
	Curriculo curriculo;
	
	public AlunoHomeMBean() {
		curriculo = new Curriculo();
		curriculoDao = new CurriculoDao();
		
	}
	
	public void listarCurriculo() throws SQLException {
		curriculo = curriculoDao.listarCurriculo(curriculo.getCurso());
	}
	
	

}
