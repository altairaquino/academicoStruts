package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanDisciplina;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelDisciplina {

	public static ModelDisciplina getInstance(){
		return new ModelDisciplina();
	}
	
	public ArrayList<BeanDisciplina> getDisciplinasDoCurso(int csncodg){
		ArrayList<BeanDisciplina> disciplinas = new ArrayList<BeanDisciplina>();
		try {
			String sql = " SELECT * FROM VW_DISCIPLINA where DCNCGCS = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, csncodg);
			
			disciplinas.addAll(Utils.getObjectsStr(st, BeanDisciplina.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return disciplinas;
		
	}
		
	public BeanDisciplina getDisciplina(int dcncodg){
		BeanDisciplina cursoSemestre = null;
		try {
			String sql = " SELECT * FROM VW_DISCIPLINA where DCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, dcncodg);
			
			List<BeanDisciplina> l = Utils.getObjectsStr(st, BeanDisciplina.class); 
			
			if (!l.isEmpty())
				cursoSemestre = l .get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cursoSemestre;
	}

	public void inserir(BeanDisciplina beanDisciplina){
		try {
			String sql = "INSERT INTO DISCIPLINA(,,,)" +
					" VALUES(?,?,?,?,?,?,?,?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			/*
			st.setInt(1, Integer.parseInt(beanDisciplina.getCmncgsm()));
			st.setInt(2, Integer.parseInt(beanDisciplina.getCmncgcs()));
			st.setInt(3, Integer.parseInt(beanDisciplina.getCmncgue()));
			st.setInt(4, Integer.parseInt(beanDisciplina.getCmncgtn()));
			st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(beanDisciplina.getCmyvalr())));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(beanDisciplina.getCmymult())));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(beanDisciplina.getCmyjuro())));
			st.setFloat(8, Float.parseFloat(Utils.converteFloatBR(beanDisciplina.getCmydesc())));
			*/
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}