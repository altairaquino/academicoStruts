package br.com.falconsistemas.academico.struts.form;

import org.apache.struts.action.ActionForm;

public class FormProfessor extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String pfncodg;
	private String pfcnome;
	
	public String getPfncodg() {
		return pfncodg;
	}
	public void setPfncodg(String pfncodg) {
		this.pfncodg = pfncodg;
	}
	public String getPfcnome() {
		return pfcnome;
	}
	public void setPfcnome(String pfcnome) {
		this.pfcnome = pfcnome;
	}
		

}
