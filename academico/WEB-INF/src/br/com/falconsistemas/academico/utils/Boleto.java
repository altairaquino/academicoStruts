package br.com.falconsistemas.academico.utils;

import java.text.DecimalFormat;
import java.util.Date;

public class Boleto {	
	
	private int lbddtan;
	private int lbncgsm;
	private int lbncgca;
	private int lbncgcs;
	private int lbncgtn;
	private int lbncgtb;
	private int lbncgcb;
	private int lbncgbl;
	private int lbnbanc;
	private String lbdpgto;
	private String lbcloca;
	private String lbccedt;
	private String lbccnpj;
	private Date lbdvenc;
	private String lbcvenc;
	private String vmncodg;
	private String lbcagen;
	private String lbcconv;
	private Date lbddoc; 
	private int lbnfixo;
	private int lbnnnum;
	private String lbcedoc;
	private String lbcacet;
	private Date lbdproc;
	private int lbncart;
	private String lbcespc;
	private int lbnqtd; 
	private float lbyvalr;
	private String lbcvalr;
	private float lbydoc; 
	private String lbmobs; 
	private float lbydesc;
	private float lbyoded;
	private float lbymora;
	private float lbymult;
	private float lbyoacr;
	private float lbycobr;
	private String lbcmatr;
	private int lbnentd;
	private String lbcsacd;
	private String lbccpf; 
	private String lbccurs;
	private String lbcturn;
	private String lbclogr;
	private String lbcrua; 
	private String lbcnumr;
	private String lbccomp;
	private String lbccep; 
	private String lbcuf;  
	private String lbccida;
	private String lbcbair;
	private int cbnmoed;

	public String getCodigo()throws Throwable {		
		return this.getConteudoCodBarrasLD();
	}	
	
	public Boleto(){}
		
	public String getConteudoCodBarras()throws Throwable{
		
	       String s =
	             ControleBoleto.strZero(""+this.lbnbanc,3) +
	              this.cbnmoed +
	              ControleBoleto.calculaFatorVencimento(this.lbdvenc)+
	              ControleBoleto.strZero(new DecimalFormat("0").format(100*this.lbydoc),10)+
	              this.lbncart+
	              ControleBoleto.strZero(this.lbcconv,6)+
	              '9'+
	              ControleBoleto.strZero(""+this.lbnnnum,17);
	        
	        return  s.substring(0,4)+ControleBoleto.modulo11(s)+s.substring(4);

	    }
	    
	    private String getConteudoCodBarrasLD()throws Throwable {
	    	
	        String s = getConteudoCodBarras();
	        String campo1 = s.substring(0,4)+s.substring(19,24);
	        String campo2 = s.substring(24,34);
	        String campo3 = s.substring(34,44);
	        
	        return
	                campo1+ControleBoleto.modulo10(campo1)+
	                campo2+ControleBoleto.modulo10(campo2)+
	                campo3+ControleBoleto.modulo10(campo3)+
	                s.substring(4,5)+  //campo4
	                s.substring(5,19);//campo5

	    }
	    
	    public String getConteudoCodBarrasLDFormatado()throws Throwable{
	        
	    	String s = getConteudoCodBarrasLD();
	        
	    	return  s.substring(0,5)+"."+s.substring(5,10)+" "+                //campo 1
	                s.substring(10,15)+"."+s.substring(15,21)+" "+             //campo 2
	                s.substring(21,26)+"."+s.substring(26,32)+" "+             //campo 3
	                s.substring(32,33)+" "+                                    //campo 4
	                s.substring(33);                                           //campo 5

	    }
		
	    public int getCbnmoed() {
			return cbnmoed;
		}
		public void setCbnmoed(int cbnmoed) {
			this.cbnmoed = cbnmoed;
		}
		public String getLbcacet() {
			return lbcacet;
		}
		public void setLbcacet(String lbcacet) {
			this.lbcacet = lbcacet;
		}
		public String getLbcagen() {
			return lbcagen;
		}
		public void setLbcagen(String lbcagen) {
			this.lbcagen = lbcagen;
		}
		public String getLbcbair() {
			return lbcbair;
		}
		public void setLbcbair(String lbcbair) {
			this.lbcbair = lbcbair;
		}
		public String getLbccedt() {
			return lbccedt;
		}
		public void setLbccedt(String lbccedt) {
			this.lbccedt = lbccedt;
		}
		public String getLbccep() {
			return lbccep;
		}
		public void setLbccep(String lbccep) {
			this.lbccep = lbccep;
		}
		public String getLbccida() {
			return lbccida;
		}
		public void setLbccida(String lbccida) {
			this.lbccida = lbccida;
		}
		public String getLbccnpj() {
			return lbccnpj;
		}
		public void setLbccnpj(String lbccnpj) {
			this.lbccnpj = lbccnpj;
		}
		public String getLbccomp() {
			return lbccomp;
		}
		public void setLbccomp(String lbccomp) {
			this.lbccomp = lbccomp;
		}
		public String getLbcconv() {
			return lbcconv;
		}
		public void setLbcconv(String lbcconv) {
			this.lbcconv = lbcconv;
		}
		public String getLbccpf() {
			return lbccpf;
		}
		public void setLbccpf(String lbccpf) {
			this.lbccpf = lbccpf;
		}
		public String getLbccurs() {
			return lbccurs;
		}
		public void setLbccurs(String lbccurs) {
			this.lbccurs = lbccurs;
		}
		public String getLbcedoc() {
			return lbcedoc;
		}
		public void setLbcedoc(String lbcedoc) {
			this.lbcedoc = lbcedoc;
		}
		public String getLbcespc() {
			return lbcespc;
		}
		public void setLbcespc(String lbcespc) {
			this.lbcespc = lbcespc;
		}
		public String getLbcloca() {
			return lbcloca;
		}
		public void setLbcloca(String lbcloca) {
			this.lbcloca = lbcloca;
		}
		public String getLbclogr() {
			return lbclogr;
		}
		public void setLbclogr(String lbclogr) {
			this.lbclogr = lbclogr;
		}
		public String getLbcmatr() {
			return lbcmatr;
		}
		public void setLbcmatr(String lbcmatr) {
			this.lbcmatr = lbcmatr;
		}
		public String getLbcnumr() {
			return lbcnumr;
		}
		public void setLbcnumr(String lbcnumr) {
			this.lbcnumr = lbcnumr;
		}
		public String getLbcrua() {
			return lbcrua;
		}
		public void setLbcrua(String lbcrua) {
			this.lbcrua = lbcrua;
		}
		public String getLbcsacd() {
			return lbcsacd;
		}
		public void setLbcsacd(String lbcsacd) {
			this.lbcsacd = lbcsacd;
		}
		public String getLbcturn() {
			return lbcturn;
		}
		public void setLbcturn(String lbcturn) {
			this.lbcturn = lbcturn;
		}
		public String getLbcuf() {
			return lbcuf;
		}
		public void setLbcuf(String lbcuf) {
			this.lbcuf = lbcuf;
		}
		public Date getLbddoc() {
			return lbddoc;
		}
		public void setLbddoc(Date lbddoc) {
			this.lbddoc = lbddoc;
		}
		public int getLbddtan() {
			return lbddtan;
		}
		public void setLbddtan(int lbddtan) {
			this.lbddtan = lbddtan;
		}
		public Date getLbdproc() {
			return lbdproc;
		}
		public void setLbdproc(Date lbdproc) {
			this.lbdproc = lbdproc;
		}
		public Date getLbdvenc() {
			return lbdvenc;
		}
		public void setLbdvenc(Date lbdvenc) {
			this.lbdvenc = lbdvenc;
		}
		public String getLbmobs() {
			return lbmobs;
		}
		public void setLbmobs(String lbmobs) {
			this.lbmobs = lbmobs;
		}
		public int getLbnbanc() {
			return lbnbanc;
		}
		public void setLbnbanc(int lbnbanc) {
			this.lbnbanc = lbnbanc;
		}
		public int getLbncart() {
			return lbncart;
		}
		public void setLbncart(int lbncart) {
			this.lbncart = lbncart;
		}
		public int getLbncgbl() {
			return lbncgbl;
		}
		public void setLbncgbl(int lbncgbl) {
			this.lbncgbl = lbncgbl;
		}
		public int getLbncgca() {
			return lbncgca;
		}
		public void setLbncgca(int lbncgca) {
			this.lbncgca = lbncgca;
		}
		public int getLbncgcb() {
			return lbncgcb;
		}
		public void setLbncgcb(int lbncgcb) {
			this.lbncgcb = lbncgcb;
		}
		public int getLbncgcs() {
			return lbncgcs;
		}
		public void setLbncgcs(int lbncgcs) {
			this.lbncgcs = lbncgcs;
		}
		public int getLbncgsm() {
			return lbncgsm;
		}
		public void setLbncgsm(int lbncgsm) {
			this.lbncgsm = lbncgsm;
		}
		public int getLbncgtb() {
			return lbncgtb;
		}
		public void setLbncgtb(int lbncgtb) {
			this.lbncgtb = lbncgtb;
		}
		public int getLbncgtn() {
			return lbncgtn;
		}
		public void setLbncgtn(int lbncgtn) {
			this.lbncgtn = lbncgtn;
		}
		public int getLbnentd() {
			return lbnentd;
		}
		public void setLbnentd(int lbnentd) {
			this.lbnentd = lbnentd;
		}
		public int getLbnfixo() {
			return lbnfixo;
		}
		public void setLbnfixo(int lbnfixo) {
			this.lbnfixo = lbnfixo;
		}
		public int getLbnnnum() {
			return lbnnnum;
		}
		public void setLbnnnum(int lbnnnum) {
			this.lbnnnum = lbnnnum;
		}
		public int getLbnqtd() {
			return lbnqtd;
		}
		public void setLbnqtd(int lbnqtd) {
			this.lbnqtd = lbnqtd;
		}
		public float getLbycobr() {
			return lbycobr;
		}
		public void setLbycobr(float lbycobr) {
			this.lbycobr = lbycobr;
		}
		public float getLbydesc() {
			return lbydesc;
		}
		public void setLbydesc(float lbydesc) {
			this.lbydesc = lbydesc;
		}
		public float getLbydoc() {
			return lbydoc;
		}
		public void setLbydoc(float lbydoc) {
			this.lbydoc = lbydoc;
		}
		public float getLbymora() {
			return lbymora;
		}
		public void setLbymora(float lbymora) {
			this.lbymora = lbymora;
		}
		public float getLbymult() {
			return lbymult;
		}
		public void setLbymult(float lbymult) {
			this.lbymult = lbymult;
		}
		public float getLbyoacr() {
			return lbyoacr;
		}
		public void setLbyoacr(float lbyoacr) {
			this.lbyoacr = lbyoacr;
		}
		public float getLbyoded() {
			return lbyoded;
		}
		public void setLbyoded(float lbyoded) {
			this.lbyoded = lbyoded;
		}
		public float getLbyvalr() {
			return lbyvalr;
		}
		public void setLbyvalr(float lbyvalr) {
			this.lbyvalr = lbyvalr;
		}
		public String getLbdpgto() {
			return lbdpgto;
		}
		public void setLbdpgto(String lbdpgto) {
			this.lbdpgto = lbdpgto;
		}
		public String getLbcvenc() {
			return lbcvenc;
		}
		public void setLbcvenc(String lbcvenc) {
			this.lbcvenc = lbcvenc;
		}
		public String getVmncodg() {
			return vmncodg;
		}
		public void setVmncodg(String vmncodg) {
			this.vmncodg = vmncodg;
		}
		public String getLbcvalr() {
			return lbcvalr;
		}
		public void setLbcvalr(String lbcvalr) {
			this.lbcvalr = lbcvalr;
		}
}
