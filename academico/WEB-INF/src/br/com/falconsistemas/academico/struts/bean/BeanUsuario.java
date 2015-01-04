package br.com.falconsistemas.academico.struts.bean;

import java.io.Serializable;

public class BeanUsuario implements Serializable{
	
	private static final long serialVersionUID = -7101847679210286297L;
	
	private String usncodg;
	private String uscnome;
	private String usclogn;
	private String uscpswd;
	private String uslativ;
	
	public String getUsncodg() {
		return usncodg;
	}
	public void setUsncodg(String usncodg) {
		this.usncodg = usncodg;
	}
	public String getUscnome() {
		return uscnome;
	}
	public void setUscnome(String uscnome) {
		this.uscnome = uscnome;
	}
	public String getUsclogn() {
		return usclogn;
	}
	public void setUsclogn(String usclogn) {
		this.usclogn = usclogn;
	}
	public String getUscpswd() {
		return uscpswd;
	}
	public void setUscpswd(String uscpswd) {
		this.uscpswd = uscpswd;
	}
	public String getUslativ() {
		return uslativ;
	}
	public void setUslativ(String uslativ) {
		this.uslativ = uslativ;
	}
	

}
