package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanEntidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String enncodg;
	private String encdesc;
	private String enytxmt;
	
	public String getEnncodg() {
		return enncodg;
	}
	public void setEnncodg(String enncodg) {
		this.enncodg = enncodg;
	}
	public String getEncdesc() {
		return encdesc;
	}
	public void setEncdesc(String encdesc) {
		this.encdesc = encdesc;
	}
	public String getEnytxmt() {
		return enytxmt;
	}
	public void setEnytxmt(String enytxmt) {
		this.enytxmt = enytxmt;
	}
	
	
		

}
