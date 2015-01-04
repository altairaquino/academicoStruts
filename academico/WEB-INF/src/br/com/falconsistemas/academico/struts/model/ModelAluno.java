package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanAluno;
import br.com.falconsistemas.academico.utils.Utils;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ModelAluno {

	public static ModelAluno getInstance(){
		return new ModelAluno();
	}
			
	public BeanAluno getAluno(int alncodg){
		BeanAluno aluno = null;
		try {
			String sql = "SELECT * FROM VW_ALUNO WHERE ALNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			List<BeanAluno> l = Utils.getObjectsStr(st, BeanAluno.class); 
			
			if (!l.isEmpty())
				aluno = l .get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return aluno;
	}
	
	public List<BeanAluno> getAlunosPorNomeCpf(String alcnome){
		List<BeanAluno> alunos = new ArrayList<BeanAluno>();
		try {
			String sql = "SELECT * FROM VW_ALUNO WHERE (ALCNOME LIKE upper('%"+alcnome.toUpperCase()+"%') OR ALCCPF = '"+alcnome.toUpperCase()+"')";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			alunos = Utils.getObjectsStr(st, BeanAluno.class);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return alunos;
	}
	
	public List<BeanAluno> getAlunosPorNomeCpfSemMatricula(String alcnome){
		List<BeanAluno> alunos = new ArrayList<BeanAluno>();
		try {
			String sql = " SELECT * FROM VW_ALUNO WHERE (ALCNOME LIKE upper('%"+alcnome.toUpperCase()+"%') OR ALCCPF = '"+alcnome.toUpperCase()+"')"+
						" AND NOT EXISTS (SELECT MTNCGAL FROM MATRICULA " +
						" WHERE MTNCGAL = ALNCODG AND MTNCGSR = 2)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			alunos = Utils.getObjectsStr(st, BeanAluno.class);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return alunos;
	}
	
	public List<BeanAluno> getAlunosPorNomeCpfComMatricula(String alcnome){
		List<BeanAluno> alunos = new ArrayList<BeanAluno>();
		try {
			String sql = " SELECT * FROM VW_ALUNO WHERE (ALCNOME LIKE upper('%"+alcnome.toUpperCase()+"%') OR ALCCPF = '"+alcnome.toUpperCase()+"')"+
						" AND EXISTS (SELECT MTNCGAL FROM MATRICULA " +
						" WHERE MTNCGAL = ALNCODG AND MTNCGSR = 2)" +
						" ORDER BY ALCNOME";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			alunos = Utils.getObjectsStr(st, BeanAluno.class);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return alunos;
	}

	public void inserir(BeanAluno beanAluno) {
		try {
			String sql = "INSERT INTO ALUNO(ALCNOME,ALCCPF,ALDNASC,ALNCDNC,ALCPAI,ALCMAE,ALNCGEC, " +
					     "ALCRG,ALCOERG,ALCUFRG,ALCTIEL,ALCZOTE,ALCSETE,ALDDETE,ALCNACI,ALCFONE," +
					     "ALCCELL,ALNCGTL,ALCLOGR,ALNCDED,ALCCOMP,ALCCEP,ALCBAIR,ALCIESG,ALCSEXO)" +
					     " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, beanAluno.getAlcnome().toUpperCase());
			st.setString(2, ValidaObjeto.removeCharOfInteger(beanAluno.getAlccpf()));
			st.setDate(3, Utils.stringToDateSQL(beanAluno.getAldnasc()));
			st.setInt(4, Integer.parseInt(beanAluno.getAlncdnc()));
			if (beanAluno.getAlcpai() != null){
				st.setString(5, beanAluno.getAlcpai().toUpperCase());
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			st.setString(6, beanAluno.getAlcmae().toUpperCase());
			st.setInt(7, Integer.parseInt(beanAluno.getAlncgec()));
			st.setString(8, beanAluno.getAlcrg());
			st.setString(9, beanAluno.getAlcoerg().toUpperCase());
			st.setString(10, beanAluno.getAlcufrg());
			st.setString(11, beanAluno.getAlctiel());
			st.setString(12, beanAluno.getAlczote());
			st.setString(13, beanAluno.getAlcsete());
			st.setDate(14, Utils.stringToDateSQL(beanAluno.getAlddete()));
			st.setString(15, beanAluno.getAlcnaci().toUpperCase());
			st.setString(16, ValidaObjeto.removeCharOfInteger(beanAluno.getAlcfone()));
			st.setString(17, ValidaObjeto.removeCharOfInteger(beanAluno.getAlccell()));
			st.setInt(18, Integer.parseInt(beanAluno.getAlncgtl()));
			st.setString(19, beanAluno.getAlclogr().toUpperCase());
			st.setInt(20, Integer.parseInt(beanAluno.getAlncded()));
			st.setString(21, beanAluno.getAlccomp().toUpperCase());
			st.setString(22, ValidaObjeto.removeCharOfInteger(beanAluno.getAlccep()));
			st.setString(23, beanAluno.getAlcbair().toUpperCase());
			st.setString(24, beanAluno.getAlciesg().toUpperCase());
			st.setString(25, beanAluno.getAlcsexo());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean cpfExiste(String alccpf) {
		boolean ret = true;
		try {
			String sql = "SELECT ALCCPF FROM VW_ALUNO WHERE ALCCPF = '"+alccpf+"'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			ret = st.executeQuery().next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public void update(BeanAluno beanAluno) {
		try {
			String sql = "UPDATE ALUNO SET ALCNOME = ?,ALCCPF = ?,ALDNASC = ?,ALNCDNC = ?,ALCPAI = ?,ALCMAE = ?,ALNCGEC = ?, " +
					     "ALCRG = ?,ALCOERG = ?,ALCUFRG = ?,ALCTIEL = ?,ALCZOTE = ?,ALCSETE = ?,ALDDETE = ?,ALCNACI = ?,ALCFONE = ?," +
					     "ALCCELL = ?,ALNCGTL = ?,ALCLOGR = ?,ALNCDED = ?,ALCCOMP = ?,ALCCEP = ?,ALCBAIR = ?,ALCIESG = ?, ALCSEXO = ?" +
					     " WHERE ALNCODG = ? ";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, beanAluno.getAlcnome().toUpperCase());
			st.setString(2, ValidaObjeto.removeCharOfInteger(beanAluno.getAlccpf()));
			st.setDate(3, Utils.stringToDateSQL(beanAluno.getAldnasc()));
			st.setInt(4, Integer.parseInt(beanAluno.getAlncdnc()));
			
			if (beanAluno.getAlcpai() != null){
				st.setString(5, beanAluno.getAlcpai().toUpperCase());
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			
			st.setString(6, beanAluno.getAlcmae().toUpperCase());
			st.setInt(7, Integer.parseInt(beanAluno.getAlncgec()));
			st.setString(8, beanAluno.getAlcrg());
			st.setString(9, beanAluno.getAlcoerg().toUpperCase());
			st.setString(10, beanAluno.getAlcufrg());
			st.setString(11, beanAluno.getAlctiel());
			st.setString(12, beanAluno.getAlczote());
			st.setString(13, beanAluno.getAlcsete());
			st.setDate(14, Utils.stringToDateSQL(beanAluno.getAlddete()));
			st.setString(15, beanAluno.getAlcnaci().toUpperCase());
			st.setString(16, ValidaObjeto.removeCharOfInteger(beanAluno.getAlcfone()));
			st.setString(17, ValidaObjeto.removeCharOfInteger(beanAluno.getAlccell()));
			st.setInt(18, Integer.parseInt(beanAluno.getAlncgtl()));
			st.setString(19, beanAluno.getAlclogr().toUpperCase());
			st.setInt(20, Integer.parseInt(beanAluno.getAlncded()));
			st.setString(21, beanAluno.getAlccomp().toUpperCase());
			st.setString(22, ValidaObjeto.removeCharOfInteger(beanAluno.getAlccep()));
			st.setString(23, beanAluno.getAlcbair().toUpperCase());
			st.setString(24, beanAluno.getAlciesg().toUpperCase());
			st.setString(25, beanAluno.getAlcsexo());
			st.setInt(26, Integer.parseInt(beanAluno.getAlncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}