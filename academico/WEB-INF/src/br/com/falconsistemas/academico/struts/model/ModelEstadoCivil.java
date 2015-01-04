package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanEstadoCivil;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelEstadoCivil {

	public static ModelEstadoCivil getInstance(){
		return new ModelEstadoCivil();
	}
	
	public ArrayList<BeanEstadoCivil> getEstadosCivis(){
		ArrayList<BeanEstadoCivil> estados = new ArrayList<BeanEstadoCivil>();
		try {
			String sql = " SELECT ECNCODG, ECCDESC FROM VW_ESTADOCIVIL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			estados.addAll(Utils.getObjectsStr(st, BeanEstadoCivil.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return estados;
		
	}
	
	public BeanEstadoCivil getEstadoCivil(int ecncodg){
		BeanEstadoCivil estadoCivil = null;
		try {
			String sql = "  SELECT ECNCODG, ECCDESC FROM VW_ESTADOCIVIL WHERE ECNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ecncodg);
			
			List<BeanEstadoCivil> l = Utils.getObjectsStr(st, BeanEstadoCivil.class); 
			
			if (!l.isEmpty())
				estadoCivil = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return estadoCivil;
		
	}
}
