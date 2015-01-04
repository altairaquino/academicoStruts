package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanProfessor;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelProfessor {

	public static ModelProfessor getInstance(){
		return new ModelProfessor();
	}
	
	public ArrayList<BeanProfessor> getProfessores(){
		ArrayList<BeanProfessor> professores = new ArrayList<BeanProfessor>();
		try {
			String sql = " SELECT PFNCODG, PFCNOME FROM VW_PROFESSOR";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			professores.addAll(Utils.getObjectsStr(st, BeanProfessor.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return professores;
		
	}
	
	public BeanProfessor getProfessor(int pfncodg){
		BeanProfessor professor = null;
		try {
			String sql = " SELECT PFNCODG, PFCNOME FROM VW_PROFESSOR WHERE PFNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pfncodg);
			
			List<BeanProfessor> l = Utils.getObjectsStr(st, BeanProfessor.class); 
			
			if (!l.isEmpty())
				professor = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return professor;
		
	}

	public void inserir(BeanProfessor beanProfessor) {
		try {
			
			String sql = " INSERT INTO PROFESSOR (PFCNOME) VALUES (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, beanProfessor.getPfcnome().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
