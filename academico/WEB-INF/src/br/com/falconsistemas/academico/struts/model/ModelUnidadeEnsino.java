package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanUnidadeEnsino;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelUnidadeEnsino {

	public static ModelUnidadeEnsino getInstance(){
		return new ModelUnidadeEnsino();
	}
	
	public ArrayList<BeanUnidadeEnsino> getUnidadesEnsino(){
		ArrayList<BeanUnidadeEnsino> unidades = new ArrayList<BeanUnidadeEnsino>();
		try {
			String sql = "  SELECT UENCODG, UECDESC FROM VW_UNIDADEENSINO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			unidades.addAll(Utils.getObjectsStr(st, BeanUnidadeEnsino.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return unidades;
		
	}
	
	public BeanUnidadeEnsino getUnidadeEnsino(int uencodg){
		BeanUnidadeEnsino unidade = null;
		try {
			String sql = "  SELECT UENCODG, UECDESC FROM VW_UNIDADEENSINO where UENCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, uencodg);
			
			List<BeanUnidadeEnsino> l = Utils.getObjectsStr(st, BeanUnidadeEnsino.class); 
			
			if (!l.isEmpty())
				unidade = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return unidade;
		
	}
}
