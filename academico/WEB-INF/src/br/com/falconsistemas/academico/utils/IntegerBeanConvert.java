package br.com.falconsistemas.academico.utils;

import org.apache.commons.beanutils.Converter;

public class IntegerBeanConvert implements Converter {
	
	public Object convert(Class arg0, Object arg1) {		
		String str = "";
		if (arg1 instanceof String) {
			str = (String) arg1;
		}
		else if (arg1 instanceof Integer) {
			str = ((Integer) arg1).toString();
		}
		return (str == null || str.equals("") || str.equals("0") ? null : new Integer(str));
	}

}
