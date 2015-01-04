package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanTipoIngresso;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelTipoIngresso {

	public static ModelTipoIngresso getInstance(){
		return new ModelTipoIngresso();
	}
	
	public ArrayList<BeanTipoIngresso> getTiposIngresso(){
		ArrayList<BeanTipoIngresso> tis = new ArrayList<BeanTipoIngresso>();
		try {
		
			String sql = " SELECT TINCODG, TICDESC FROM TIPOINGRESSO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tis.addAll(Utils.getObjectsStr(st, BeanTipoIngresso.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tis;
		
	}
	
	public BeanTipoIngresso getTipoIngresso(int tincodg){
		BeanTipoIngresso tipo = null;
		try {
			String sql = " SELECT TINCODG, TICDESC FROM TIPOINGRESSO WHERE TINCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tincodg);
			
			List<BeanTipoIngresso> l = Utils.getObjectsStr(st, BeanTipoIngresso.class); 
			
			if (!l.isEmpty())
				tipo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipo;
		
	}
}
