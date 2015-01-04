package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanCurso;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelCurso {

	public static ModelCurso getInstance(){
		return new ModelCurso();
	}
	
	public ArrayList<BeanCurso> getCursos(){
		ArrayList<BeanCurso> cursos = new ArrayList<BeanCurso>();
		try {
			String sql = " SELECT CSNCODG, CSCDESC, CSCTIPO, CSNMDIA, CSCSIGL FROM VW_CURSO ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			cursos.addAll(Utils.getObjectsStr(st, BeanCurso.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cursos;
		
	}
	
	public BeanCurso getCurso(int csncodg){
		BeanCurso curso = null;
		try {
			String sql = "SELECT CSNCODG, CSCDESC, CSCTIPO, CSNMDIA, CSCSIGL FROM VW_CURSO where CSNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, csncodg);
			
			List<BeanCurso> l = Utils.getObjectsStr(st, BeanCurso.class); 
			
			if (!l.isEmpty())
				curso = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return curso;
		
	}
}
