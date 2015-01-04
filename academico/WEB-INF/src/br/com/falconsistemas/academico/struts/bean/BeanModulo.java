package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanModulo implements Serializable {
	
	private static final long serialVersionUID = 5170663194804258565L;
	
	private String moncodg;
	private String mocdesc;
	private String moncgcs;
	private String mocdccs;
	
	public String getMoncodg() {
		return moncodg;
	}
	public void setMoncodg(String moncodg) {
		this.moncodg = moncodg;
	}
	public String getMocdesc() {
		return mocdesc;
	}
	public void setMocdesc(String mocdesc) {
		this.mocdesc = mocdesc;
	}
	public String getMoncgcs() {
		return moncgcs;
	}
	public void setMoncgcs(String moncgcs) {
		this.moncgcs = moncgcs;
	}
	public String getMocdccs() {
		return mocdccs;
	}
	public void setMocdccs(String mocdccs) {
		this.mocdccs = mocdccs;
	}
	
	

}
