package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanCurso implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String csncodg;
	private String cscdesc;
	private String csctipo;
	private String csnmdia;
	private String cscsigl;
	
	public String getCsncodg() {
		return csncodg;
	}
	public void setCsncodg(String csncodg) {
		this.csncodg = csncodg;
	}
	public String getCscdesc() {
		return cscdesc;
	}
	public void setCscdesc(String cscdesc) {
		this.cscdesc = cscdesc;
	}
	public String getCsctipo() {
		return csctipo;
	}
	public void setCsctipo(String csctipo) {
		this.csctipo = csctipo;
	}
	public String getCsnmdia() {
		return csnmdia;
	}
	public void setCsnmdia(String csnmdia) {
		this.csnmdia = csnmdia;
	}
	public String getCscsigl() {
		return cscsigl;
	}
	public void setCscsigl(String cscsigl) {
		this.cscsigl = cscsigl;
	}


}
