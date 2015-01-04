package br.com.falconsistemas.academico.struts.form;

import org.apache.struts.action.ActionForm;

public class FormUnidadeEnsino extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String uencodg;
	private String uecdesc;
	
	public String getUencodg() {
		return uencodg;
	}
	public void setUencodg(String uencodg) {
		this.uencodg = uencodg;
	}
	public String getUecdesc() {
		return uecdesc;
	}
	public void setUecdesc(String uecdesc) {
		this.uecdesc = uecdesc;
	}

}
