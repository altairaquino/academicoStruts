package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanTurma;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelTurma {

	public static ModelTurma getInstance(){
		return new ModelTurma();
	}
	
	public ArrayList<BeanTurma> getTurmasCursoSemestre(int cmncodg){
		ArrayList<BeanTurma> turmas = new ArrayList<BeanTurma>();
		try {
			String sql = " SELECT * FROM VW_TURMA WHERE TMNCGCM = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cmncodg);
			
			turmas.addAll(Utils.getObjectsStr(st, BeanTurma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return turmas;
		
	}
	
	public ArrayList<BeanTurma> getTurmasParaMatricula(int alncodg){
		ArrayList<BeanTurma> turmas = new ArrayList<BeanTurma>();
		try {
			String sql = " SELECT DISTINCT TMNCODG, TMNCGCM, TMNCGDC, TMNPERI, TMNCGPF," +
					     " TMNMAX, TMNMATR, TMLEXTR, CAST(TMNPERI AS VARCHAR(10))||'º PERÍODO - '||TMCSGCS||' ('||CMCDCUE||') '||TMCDCDC TMCDCDC" +
					     " FROM VW_TURMA LEFT JOIN VW_CURSOSEMESTRE ON" +
					     "    CMNCODG = TMNCGCM" +
					     " LEFT JOIN SEMESTRE ON" +
					     "    SMNCODG = CMNCGSM" +
					     " LEFT JOIN MATRICULA ON "+
					     "    MTNCGCS = CMNCGCS AND"+ 
					     "	  MTNCGUE = CMNCGUE" +
					     " WHERE NOT EXISTS (SELECT MATRICULATEMP.M2NCGTM" +
					     "          FROM MATRICULATEMP LEFT JOIN MATRICULA ON" +
					     "            MTNCODG = M2NCGMT" +
					     "            WHERE M2NCGTM = TMNCODG" +
					     "            AND MTNCGAL = ?)" +
					     " AND ('NOW' BETWEEN SMDABER AND SMDFECH)" +
					     " AND MTNCGAL = ?" +
					     " ORDER BY TMNPERI";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			st.setInt(2, alncodg);
			
			turmas.addAll(Utils.getObjectsStr(st, BeanTurma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turmas;		
	}
	
	public ArrayList<BeanTurma> getTurmasParaMatriculaTemp(int alncodg){
		ArrayList<BeanTurma> turmas = new ArrayList<BeanTurma>();
		try {
			String sql = " SELECT DISTINCT TMNCODG, TMNCGCM, TMNCGDC, TMNPERI, TMNCGPF," +
					     " TMNMAX, TMNMATR, TMLEXTR, CAST(TMNPERI AS VARCHAR(10))||'º PERÍODO - '||TMCSGCS||' ('||CMCDCUE||') '||TMCDCDC TMCDCDC" +
					     " FROM VW_TURMA LEFT JOIN VW_CURSOSEMESTRE ON" +
					     "    CMNCODG = TMNCGCM" +
					     " LEFT JOIN SEMESTRE ON" +
					     "    SMNCODG = CMNCGSM" +
					     " LEFT JOIN MATRICULA ON "+
					     "    MTNCGCS = CMNCGCS AND" +
					     "	  MTNCGUE = CMNCGUE" +
					     " WHERE EXISTS (SELECT MATRICULATEMP.M2NCGTM" +
					     "          FROM MATRICULATEMP LEFT JOIN MATRICULA ON" +
					     "            MTNCODG = M2NCGMT" +
					     "            WHERE M2NCGTM = TMNCODG" +
					     "            AND MTNCGAL = ?)" +
					     " AND ('NOW' BETWEEN SMDABER AND SMDFECH)" +
					     " AND MTNCGAL = ? " +
					     " ORDER BY TMNPERI";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			st.setInt(2, alncodg);
			
			turmas.addAll(Utils.getObjectsStr(st, BeanTurma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turmas;		
	}
	
	public BeanTurma getTurma(int tmncodg){
		BeanTurma turma = null;
		try {
			String sql = " SELECT * FROM VW_TURMA where TMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tmncodg);
			
			List<BeanTurma> l = Utils.getObjectsStr(st, BeanTurma.class); 
			
			if (!l.isEmpty())
				turma = l .get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return turma;
	}

	public void inserir(BeanTurma beanTurma) {
		try {
			String sql = " INSERT INTO TURMA(TMNCGCM,TMNCGPF,TMNCGDC,TMNPERI,TMLEXTR,TMNMAX)" +
					     " VALUES(?,?,?,?,?,?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanTurma.getTmncgcm()));
			st.setInt(2, Integer.parseInt(beanTurma.getTmncgpf()));
			st.setInt(3, Integer.parseInt(beanTurma.getTmncgdc()));
			st.setInt(4, Integer.parseInt(beanTurma.getTmnperi()));
			st.setString(5, beanTurma.getTmlextr());
			st.setInt(6, Integer.parseInt(beanTurma.getTmnmax()));
					
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void importaTurmasCursoSemestre(int cmncodg) {
		try {
			String sql = " EXECUTE PROCEDURE SP_IMPORTA_TURMAS(?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, cmncodg);					
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanTurma beanTurma) {
		try {
			String sql = " UPDATE TURMA SET " +
					     " TMNCGCM = ?," +
					     " TMNCGPF = ?," +
					     " TMNCGDC = ?," +
					     " TMNPERI = ?," +
					     " TMLEXTR = ?," +
					     " TMNMAX = ? " +
					     " WHERE TMNCODG = ? ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanTurma.getTmncgcm()));
			st.setInt(2, Integer.parseInt(beanTurma.getTmncgpf()));
			st.setInt(3, Integer.parseInt(beanTurma.getTmncgdc()));
			st.setInt(4, Integer.parseInt(beanTurma.getTmnperi()));
			st.setString(5, beanTurma.getTmlextr());
			st.setInt(6, Integer.parseInt(beanTurma.getTmnmax()));
			st.setInt(7, Integer.parseInt(beanTurma.getTmncodg()));
					
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}