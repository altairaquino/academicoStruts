package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanMatricula;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelMatricula {

	public static ModelMatricula getInstance(){
		return new ModelMatricula();
	}
	
	public BeanMatricula getMatricula(int mtncodg){
		BeanMatricula matricula = null;
		try {
			String sql = " SELECT * FROM VW_MATRICULA where MTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, mtncodg);
			
			List<BeanMatricula> l = Utils.getObjectsStr(st, BeanMatricula.class); 
			
			if (!l.isEmpty()){
				matricula = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return matricula;
		
	}
	
	public BeanMatricula getMatriculaDoAluno(int alncodg){
		BeanMatricula matricula = null;
		try {
			String sql = " SELECT * FROM VW_MATRICULA where MTNCGAL = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			List<BeanMatricula> l = Utils.getObjectsStr(st, BeanMatricula.class); 
			
			if (!l.isEmpty()){
				matricula = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return matricula;
		
	}
			
	
	public void inserir(BeanMatricula beanMatricula) {
		try {
			String sql = "INSERT INTO MATRICULA(MTNCGAL, MTNCGCM, MTNCGTI, MTNPCDC, MTNCGSR, MTNCGUS)" +
						" VALUES(?,?,?,?,?,?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanMatricula.getMtncgal()));
			st.setInt(2, Integer.parseInt(beanMatricula.getMtncgcm()));
			st.setInt(3, Integer.parseInt(beanMatricula.getMtncgti()));
			st.setFloat(4, Float.parseFloat(beanMatricula.getMtnpcdc()));
			st.setInt(5, 2);
			st.setInt(6, Integer.parseInt(beanMatricula.getMtncgus()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void matricular(int mtncodg, int tmncodg) {
		
		try {
			String sql = "INSERT INTO MATRICULATEMP(M2NCGTM, M2NCGMT)" +
						" VALUES(?,?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, tmncodg);
			st.setInt(2, mtncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remover(int mtncodg, int tmncodg) {
		try {
			String sql = "DELETE FROM MATRICULATEMP WHERE M2NCGTM = ? AND M2NCGMT = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, tmncodg);
			st.setInt(2, mtncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BeanMatricula matriculaDoAluno(int alncodg) {
		BeanMatricula matricula = null;
		try {
			String sql = " SELECT VW_MATRICULA.* FROM VW_MATRICULA " +
					     " LEFT JOIN STATUSMATRICULA ON" +
					     "   MTNCGSR = SRNCODG" +
					     " WHERE MTNCGAL = ?" +
					     " AND SRLREMT = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			List<BeanMatricula> l = Utils.getObjectsStr(st, BeanMatricula.class); 
			
			if (!l.isEmpty()){
				matricula = l.get(0);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return matricula;
	}

	public void finaliza(int alncodg) {
		try {
			String sql = "EXECUTE PROCEDURE SP_FINALIZA_MATRICULA(?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, alncodg);
			
			st.executeUpdate();
			
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}