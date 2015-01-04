package br.com.falconsistemas.academico.utils;

import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class StringToDateBeanConvert implements Converter {

	@Override
	public Object convert(Class arg0, Object arg1) {
		String str = "";
		if (arg1 instanceof java.sql.Date || arg1 instanceof java.util.Date) {
			str = new SimpleDateFormat("dd/MM/yyyy").format(arg1);
			System.out.print(arg0.toString() + "-");
			System.out.println(arg1.getClass().toString());
		}
		Object ret = (arg1 instanceof String)? arg1 : str; 
		return ret;/**/
	}
	
}
