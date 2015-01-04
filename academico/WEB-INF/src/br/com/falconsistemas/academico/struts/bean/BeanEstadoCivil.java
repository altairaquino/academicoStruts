package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanEstadoCivil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String ecncodg;
	private String eccdesc;
	
	public String getEcncodg() {
		return ecncodg;
	}
	public void setEcncodg(String ecncodg) {
		this.ecncodg = ecncodg;
	}
	public String getEccdesc() {
		return eccdesc;
	}
	public void setEccdesc(String eccdesc) {
		this.eccdesc = eccdesc;
	}
	
		

}
