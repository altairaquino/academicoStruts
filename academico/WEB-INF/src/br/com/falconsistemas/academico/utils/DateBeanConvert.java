package br.com.falconsistemas.academico.utils;

import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateBeanConvert implements Converter {
	public Object convert(Class arg0, Object arg1) {
		Date date = null;
		if (arg1 instanceof String) {
			String str = (String) arg1;
			System.out.print("Class" + arg0.toString() + "-");
			System.out.println(arg1.getClass().toString());
			date = Utils.stringToDateSQL(str);
		}
		Object ret = (arg1 instanceof String)? date : arg1; 
		return ret;/**/
	}
	
}
