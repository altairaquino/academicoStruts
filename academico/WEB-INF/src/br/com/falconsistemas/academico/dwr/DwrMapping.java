package br.com.falconsistemas.academico.dwr;

import java.util.Iterator;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanCidade;
import br.com.falconsistemas.academico.struts.model.ModelCidade;


public class DwrMapping {
	
	public static String getCidades(String UF, String campo){
		String ret = "<select name=\""+campo+"\" style=\"width: 200px\">\n";
		try {
			
			
			List<BeanCidade> list = ModelCidade.getInstance().getCidadesDoEstado(UF);
			Iterator<BeanCidade> iter = list.iterator();
			
			while (iter.hasNext()){
				BeanCidade cidade = iter.next();
				ret += "<option value=\""+cidade.getCdncodg()+"\">"+cidade.getCdcdesc()+"</option>\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		ret += "</select>\n";
		
		return ret;
	}
	
	
}
