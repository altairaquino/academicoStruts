package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanReligiao;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelReligiao {

	public static ModelReligiao getInstance(){
		return new ModelReligiao();
	}
	
	public ArrayList<BeanReligiao> getReligioes(){
		ArrayList<BeanReligiao> turnos = new ArrayList<BeanReligiao>();
		try {
			String sql = " SELECT RLNCODG, RLCDESC FROM VW_RELIGIAO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			turnos.addAll(Utils.getObjectsStr(st, BeanReligiao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return turnos;
		
	}
	
	public BeanReligiao getReligiao(int rlncodg){
		BeanReligiao religiao = null;
		try {
			String sql = " SELECT RLNCODG, RLCDESC FROM VW_RELIGIAO WHERE RLNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, rlncodg);
			
			List<BeanReligiao> l = Utils.getObjectsStr(st, BeanReligiao.class); 
			
			if (!l.isEmpty())
				religiao = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return religiao;
		
	}
}
