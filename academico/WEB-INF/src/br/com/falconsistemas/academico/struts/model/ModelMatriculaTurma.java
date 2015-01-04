package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanMatriculaTurma;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelMatriculaTurma {

	public static ModelMatriculaTurma getInstance(){
		return new ModelMatriculaTurma();
	}
	
	public ArrayList<BeanMatriculaTurma> getMatriculasDaTurma(int tmncodg){
		ArrayList<BeanMatriculaTurma> matriculas = new ArrayList<BeanMatriculaTurma>();
		try {
			String sql = " SELECT  * FROM VW_MATRICULATURMA WHERE TTNCGTM = ? ORDER BY TTCNMAL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tmncodg);
			
			matriculas.addAll(Utils.getObjectsStr(st, BeanMatriculaTurma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return matriculas;
		
	}
	
	public ArrayList<BeanMatriculaTurma> getMatriculaTurmaDoAlunoSemestre(int alncodg, int smncodg){
		ArrayList<BeanMatriculaTurma> matriculas = new ArrayList<BeanMatriculaTurma>();
		try {
			
			String sql = " SELECT VW_MATRICULATURMA.* FROM VW_MATRICULATURMA " +
					     " LEFT JOIN MATRICULA ON" +
					     "   MTNCODG = TTNCGMT" +
					     " WHERE MTNCGAL = ?" +
					     " AND TTNCGSM = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			st.setInt(2, smncodg);
			
			matriculas.addAll(Utils.getObjectsStr(st, BeanMatriculaTurma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return matriculas;
		
	}
	
	public ArrayList<BeanMatriculaTurma> getMatriculaTurmaDoAluno(int alncodg){
		ArrayList<BeanMatriculaTurma> matriculas = new ArrayList<BeanMatriculaTurma>();
		try {
			
			String sql = " SELECT * FROM VW_MATRICULATURMA " +
					     " WHERE TTNCGAL = ?" +
					     " ORDER BY TTCDCDC";
					     
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			matriculas.addAll(Utils.getObjectsStr(st, BeanMatriculaTurma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return matriculas;
		
	}
	
	public BeanMatriculaTurma getMatriculaTurma(int ttncodg){
		BeanMatriculaTurma semestre = null;
		try {
			String sql = " SELECT  * FROM VW_MATRICULATURMA WHERE TTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ttncodg);
			
			List<BeanMatriculaTurma> l = Utils.getObjectsStr(st, BeanMatriculaTurma.class); 
			
			if (!l.isEmpty())
				semestre = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return semestre;
		
	}

	public void updateNota(BeanMatriculaTurma beanMatriculaTurma) {
		try {
			String sql = " UPDATE MATRICULATURMA SET " +
					     " TTNNOTA = ? " +
					     " WHERE TTNCODG = ? AND TTNCGTM = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(beanMatriculaTurma.getTtnnota())));
			st.setInt(2, Integer.parseInt(beanMatriculaTurma.getTtncodg()));
			st.setInt(3, Integer.parseInt(beanMatriculaTurma.getTtncgtm()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateNotaNull(BeanMatriculaTurma beanMatriculaTurma) {
		try {
			String sql = " UPDATE MATRICULATURMA SET " +
					     " TTNNOTA = ? " +
					     " WHERE TTNCODG = ? AND TTNCGTM = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setNull(1, java.sql.Types.FLOAT);
			st.setInt(2, Integer.parseInt(beanMatriculaTurma.getTtncodg()));
			st.setInt(3, Integer.parseInt(beanMatriculaTurma.getTtncgtm()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
