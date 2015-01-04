package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanTipoIngresso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tincodg;
	private String ticdesc;
	
	public String getTincodg() {
		return tincodg;
	}
	public void setTincodg(String tincodg) {
		this.tincodg = tincodg;
	}
	public String getTicdesc() {
		return ticdesc;
	}
	public void setTicdesc(String ticdesc) {
		this.ticdesc = ticdesc;
	}
	
}
