package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanMatriculaModulo;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelMatriculaModulo {

	public static ModelMatriculaModulo getInstance(){
		return new ModelMatriculaModulo();
	}
	
	public BeanMatriculaModulo getMatriculaModulo(int mmncodg){
		BeanMatriculaModulo matricula = null;
		try {
			String sql = " SELECT * FROM VW_MATRICULAMODULO where MMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, mmncodg);
			
			List<BeanMatriculaModulo> l = Utils.getObjectsStr(st, BeanMatriculaModulo.class); 
			
			if (!l.isEmpty()){
				matricula = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return matricula;
		
	}
	
	public ArrayList<BeanMatriculaModulo> getMatriculasModuloDoAluno(int alncodg){
		ArrayList<BeanMatriculaModulo> matriculas = new ArrayList<BeanMatriculaModulo>();
		try {
			String sql = " SELECT * FROM VW_MATRICULAMODULO where MMNCGAL = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			matriculas.addAll(Utils.getObjectsStr(st, BeanMatriculaModulo.class));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matriculas;
		
	}
			
	
	public void inserir(BeanMatriculaModulo beanMatriculaModulo) {
		try {
			String sql = "INSERT INTO MATRICULAMODULO(MMNCGMT, MMNCGMO, MMNCGSM)" +
						" VALUES(?,?,?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanMatriculaModulo.getMmncgmt()));
			st.setInt(2, Integer.parseInt(beanMatriculaModulo.getMmncgmo()));
			st.setInt(3, Integer.parseInt(beanMatriculaModulo.getMmncgsm()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}