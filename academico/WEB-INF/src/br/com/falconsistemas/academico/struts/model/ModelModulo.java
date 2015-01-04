package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanModulo;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelModulo {

	public static ModelModulo getInstance(){
		return new ModelModulo();
	}
	
	public ArrayList<BeanModulo> getModulos(){
		ArrayList<BeanModulo> professores = new ArrayList<BeanModulo>();
		try {
			String sql = " SELECT * FROM VW_MODULO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			professores.addAll(Utils.getObjectsStr(st, BeanModulo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return professores;
		
	}
	
	public ArrayList<BeanModulo> getModulosParaMatricula(int alncodg){
		ArrayList<BeanModulo> professores = new ArrayList<BeanModulo>();
		try {
			String sql = " SELECT * FROM VW_MODULO" +
					" WHERE NOT EXISTS(SELECT MMNCGMO FROM VW_MATRICULAMODULO WHERE MMNCGMO = MONCODG" +
					" AND MMNCGAL = ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);			
			st.setInt(1, alncodg);
			
			professores.addAll(Utils.getObjectsStr(st, BeanModulo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return professores;
		
	}
	
	public BeanModulo getModulo(int moncodg){
		BeanModulo professor = null;
		try {
			String sql = " SELECT * FROM VW_MODULO WHERE MONCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, moncodg);
			
			List<BeanModulo> l = Utils.getObjectsStr(st, BeanModulo.class); 
			
			if (!l.isEmpty())
				professor = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return professor;
		
	}

	public void inserir(BeanModulo beanModulo) {
		try {
			
			String sql = " INSERT INTO PROFESSOR (PFCNOME) VALUES (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			//st.setString(1, beanModulo.getPfcnome().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
