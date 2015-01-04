package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanEntidade;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelEntidade {

	public static ModelEntidade getInstance(){
		return new ModelEntidade();
	}
	
	public BeanEntidade getEntidade(){
		BeanEntidade entidade = null;
		try {
			String sql = " SELECT ENNCODG, ENCDESC, ENYTXMT FROM ENTIDADE WHERE ENNCODG = 1";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			List<BeanEntidade> l = Utils.getObjectsStr(st, BeanEntidade.class); 
			
			if (!l.isEmpty())
				entidade = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return entidade;		
	}
}
