package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanTurno implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tnncodg;
	private String tncdesc;
	
	public String getTnncodg() {
		return tnncodg;
	}
	public void setTnncodg(String tnncodg) {
		this.tnncodg = tnncodg;
	}
	public String getTncdesc() {
		return tncdesc;
	}
	public void setTncdesc(String tncdesc) {
		this.tncdesc = tncdesc;
	}
	
	

}
