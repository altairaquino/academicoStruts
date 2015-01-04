package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanSemestre;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelSemestre {

	public static ModelSemestre getInstance(){
		return new ModelSemestre();
	}
	
	public ArrayList<BeanSemestre> getSemestres(){
		ArrayList<BeanSemestre> semestres = new ArrayList<BeanSemestre>();
		try {
			String sql = " SELECT * FROM VW_SEMESTRE";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			semestres.addAll(Utils.getObjectsStr(st, BeanSemestre.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return semestres;
		
	}
	
	public ArrayList<BeanSemestre> getSemestresParaMatricula(int alncodg){
		ArrayList<BeanSemestre> semestres = new ArrayList<BeanSemestre>();
		try {
			String sql = " SELECT * FROM VW_SEMESTRE" +
						 " WHERE NOT EXISTS(SELECT MMNCGMO FROM VW_MATRICULAMODULO WHERE MMNCGSM = SMNCODG" +
						 " AND MMNCGAL = ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			semestres.addAll(Utils.getObjectsStr(st, BeanSemestre.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return semestres;
		
	}
	
	public BeanSemestre getSemestre(int smncodg){
		BeanSemestre semestre = null;
		try {
			String sql = " SELECT  SMNCODG, SMNCGAN, SMNANO, SMNNUMR, SMCDESC, SMDABER, SMDFECH FROM VW_SEMESTRE where SMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, smncodg);
			
			List<BeanSemestre> l = Utils.getObjectsStr(st, BeanSemestre.class); 
			
			if (!l.isEmpty())
				semestre = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return semestre;
		
	}
	
	public boolean semestreEstaAberto(int smncodg){
		boolean aberto = false;
		try {
			String sql = " SELECT  SMNCODG, SMNCGAN, SMNANO, SMNNUMR, SMCDESC, SMDABER, SMDFECH FROM VW_SEMESTRE " +
					     " where 'NOW' BETWEEN SMDABER AND SMDFECH AND SMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, smncodg);
			
			List<BeanSemestre> l = Utils.getObjectsStr(st, BeanSemestre.class); 
			
			aberto = !l.isEmpty();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return aberto;
	}

	public BeanSemestre semestreAtual() {
		BeanSemestre semestre = null;
		try {
			String sql = " SELECT  SMNCODG, SMNCGAN, SMNANO, SMNNUMR, SMCDESC, SMDABER, SMDFECH FROM VW_SEMESTRE WHERE ('NOW' BETWEEN SMDABER AND SMDFECH)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			List<BeanSemestre> l = Utils.getObjectsStr(st, BeanSemestre.class); 
			
			if (!l.isEmpty())
				semestre = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return semestre;
	}
}
