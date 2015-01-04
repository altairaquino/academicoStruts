package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanAluno;
import br.com.falconsistemas.academico.struts.bean.BeanCursoSemestre;
import br.com.falconsistemas.academico.struts.bean.BeanResumoParcelas;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelCursoSemestre {

	public static ModelCursoSemestre getInstance(){
		return new ModelCursoSemestre();
	}
	
	public ArrayList<BeanCursoSemestre> getCursosDoSemestre(int smncodg){
		ArrayList<BeanCursoSemestre> semestres = new ArrayList<BeanCursoSemestre>();
		try {
			String sql = " SELECT * FROM VW_CURSOSEMESTRE where CMNCGSM = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, smncodg);
			
			semestres.addAll(Utils.getObjectsStr(st, BeanCursoSemestre.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return semestres;
		
	}
	
	
	public ArrayList<BeanResumoParcelas> getResumoParcelas(){
		ArrayList<BeanResumoParcelas> resumoParcelas = new ArrayList<BeanResumoParcelas>();
		try {
			//String sql = " SELECT DISTINCT UECDESC UNIDADE, ANNANO||'/'||SMNNUMR SEMESTRE, TBCDESC TIPOBOLETO, COUNT(DISTINCT IIF(BLNCGPC IS NULL, 0, BLNCGPC)) PARCELA, COUNT(DISTINCT BLNCGAL) ALUNOS, COUNT(DISTINCT IIF(BLDPGTO IS NOT NULL, BLNCGAL, NULL)) PAGO  FROM    BOLETO    LEFT JOIN TIPOBOLETO ON      TBNCODG = BLNCGTB    LEFT JOIN CURSOSEMESTRE ON      BLNCGCM = CMNCODG    LEFT JOIN CURSO ON      CMNCGCS = CSNCODG    LEFT JOIN UNIDADEENSINO ON    CMNCGUE = UENCODG    LEFT JOIN SEMESTRE ON    CMNCGSM = SMNCODG    LEFT JOIN ANOLETIVO ON    SMNCGAN = ANNCODG  GROUP BY UECDESC, ANNANO||'/'||SMNNUMR, TBNCODG, TBCDESC";
			String sql = " SELECT DISTINCT UECDESC UNIDADE, ANNANO||'/'||SMNNUMR SEMESTRE, TBCDESC TIPOBOLETO, COUNT(DISTINCT IIF(BLNCGPC IS NULL, 0, BLNCGPC)) PARCELA, COUNT(DISTINCT BLNCGAL) ALUNOS, COUNT(IIF(BLDPGTO IS NOT NULL, BLNCGAL, NULL)) PAGO  FROM    BOLETO    LEFT JOIN TIPOBOLETO ON      TBNCODG = BLNCGTB    LEFT JOIN CURSOSEMESTRE ON      BLNCGCM = CMNCODG    LEFT JOIN CURSO ON      CMNCGCS = CSNCODG    LEFT JOIN UNIDADEENSINO ON    CMNCGUE = UENCODG    LEFT JOIN SEMESTRE ON    CMNCGSM = SMNCODG    LEFT JOIN ANOLETIVO ON    SMNCGAN = ANNCODG  GROUP BY UECDESC, ANNANO||'/'||SMNNUMR, TBNCODG, TBCDESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			resumoParcelas.addAll(Utils.getObjectsStr(st, BeanResumoParcelas.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return resumoParcelas;
		
	}
	
	public ArrayList<BeanCursoSemestre> getCursosDoSemestreMatricula(){
		ArrayList<BeanCursoSemestre> semestres = new ArrayList<BeanCursoSemestre>();
		try {
			String sql = " SELECT CMNCODG, CMNCGSM, CMCDCSM, CMNCGCS, (CMCDCCS ||' - '||CMCDCUE||' - '||CMCDCTN) CMCDCCS," +
					     "  CMCSGCS,CMNCGUE, CMCDCUE, CMNCGTN, CMCDCTN, CMYVALR, CMYMULT, CMYJURO, CMYDESC" +
					     " FROM VW_CURSOSEMESTRE LEFT JOIN SEMESTRE ON  CMNCGSM = SMNCODG" +
					     " WHERE 'NOW' BETWEEN SMDABER AND SMDFECH";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			semestres.addAll(Utils.getObjectsStr(st, BeanCursoSemestre.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return semestres;
		
	}
	
	public BeanCursoSemestre getCursoSemestre(int cmncodg){
		BeanCursoSemestre cursoSemestre = null;
		try {
			String sql = " SELECT * FROM VW_CURSOSEMESTRE where CMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cmncodg);
			
			List<BeanCursoSemestre> l = Utils.getObjectsStr(st, BeanCursoSemestre.class); 
			
			if (!l.isEmpty())
				cursoSemestre = l .get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cursoSemestre;
	}

	public void inserir(BeanCursoSemestre beanCursoSemestre) {
		try {
			String sql = "INSERT INTO CURSOSEMESTRE(CMNCGSM,CMNCGCS,CMNCGUE,CMNCGTN,CMYVALR,CMYMULT,CMYJURO,CMYDESC)" +
					" VALUES(?,?,?,?,?,?,?,?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanCursoSemestre.getCmncgsm()));
			st.setInt(2, Integer.parseInt(beanCursoSemestre.getCmncgcs()));
			st.setInt(3, Integer.parseInt(beanCursoSemestre.getCmncgue()));
			st.setInt(4, Integer.parseInt(beanCursoSemestre.getCmncgtn()));
			st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmyvalr())));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmymult())));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmyjuro())));
			st.setFloat(8, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmydesc())));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BeanAluno> getListaAlunosMatriculados(int cmncodg) {
		ArrayList<BeanAluno> alunos = new ArrayList<BeanAluno>();
		try {
			
			String sql = " SELECT * FROM VW_ALUNO WHERE " +
					     " EXISTS (SELECT TTNCGMT FROM MATRICULATURMA" +
					     "    LEFT JOIN MATRICULA ON" +
					     "       TTNCGMT = MTNCODG" +
					     "    LEFT JOIN TURMA ON" +
					     "       TTNCGTM = TMNCODG" +
					     "    WHERE MTNCGAL = ALNCODG AND TMNCGCM = ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cmncodg);
						
			alunos.addAll(Utils.getObjectsStr(st, BeanAluno.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return alunos;
	}

	public void update(BeanCursoSemestre beanCursoSemestre) {
		try {
			String sql = " UPDATE CURSOSEMESTRE SET" +
					     " CMNCGSM = ?," +
					     " CMNCGCS = ?," +
					     " CMNCGUE = ?," +
					     " CMNCGTN = ?," +
					     " CMYVALR = ?," +
					     " CMYMULT = ?," +
					     " CMYJURO = ?," +
					     " CMYDESC = ?" +
					" WHERE CMNCODG = ? ";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanCursoSemestre.getCmncgsm()));
			st.setInt(2, Integer.parseInt(beanCursoSemestre.getCmncgcs()));
			st.setInt(3, Integer.parseInt(beanCursoSemestre.getCmncgue()));
			st.setInt(4, Integer.parseInt(beanCursoSemestre.getCmncgtn()));
			st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmyvalr())));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmymult())));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmyjuro())));
			st.setFloat(8, Float.parseFloat(Utils.converteFloatBR(beanCursoSemestre.getCmydesc())));
			st.setInt(9, Integer.parseInt(beanCursoSemestre.getCmncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}