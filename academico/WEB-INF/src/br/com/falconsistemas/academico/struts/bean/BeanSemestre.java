package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanSemestre implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String smncodg;
	private String smncgan;
	private String smnano;
	private String smnnumr;
	private String smcdesc;
	private String smdaber;
	private String smdfech;
	public String getSmncodg() {
		return smncodg;
	}
	public void setSmncodg(String smncodg) {
		this.smncodg = smncodg;
	}
	public String getSmncgan() {
		return smncgan;
	}
	public void setSmncgan(String smncgan) {
		this.smncgan = smncgan;
	}
	public String getSmnano() {
		return smnano;
	}
	public void setSmnano(String smnano) {
		this.smnano = smnano;
	}
	public String getSmnnumr() {
		return smnnumr;
	}
	public void setSmnnumr(String smnnumr) {
		this.smnnumr = smnnumr;
	}
	public String getSmcdesc() {
		return smcdesc;
	}
	public void setSmcdesc(String smcdesc) {
		this.smcdesc = smcdesc;
	}
	public String getSmdaber() {
		return smdaber;
	}
	public void setSmdaber(String smdaber) {
		this.smdaber = smdaber;
	}
	public String getSmdfech() {
		return smdfech;
	}
	public void setSmdfech(String smdfech) {
		this.smdfech = smdfech;
	}
	
	

}
