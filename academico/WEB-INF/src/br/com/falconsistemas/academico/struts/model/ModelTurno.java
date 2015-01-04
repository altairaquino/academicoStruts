package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanTurno;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelTurno {

	public static ModelTurno getInstance(){
		return new ModelTurno();
	}
	
	public ArrayList<BeanTurno> getTurnos(){
		ArrayList<BeanTurno> turnos = new ArrayList<BeanTurno>();
		try {
			String sql = "  SELECT TNNCODG, TNCDESC FROM VW_TURNO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			turnos.addAll(Utils.getObjectsStr(st, BeanTurno.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return turnos;
		
	}
	
	public BeanTurno getTurno(int tnncodg){
		BeanTurno turno = null;
		try {
			String sql = "  SELECT TNNCODG, TNCDESC FROM VW_TURNO where TNNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tnncodg);
			
			List<BeanTurno> l = Utils.getObjectsStr(st, BeanTurno.class); 
			
			if (!l.isEmpty())
				turno = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return turno;
		
	}
}
