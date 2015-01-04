package br.com.falconsistemas.academico.utils;

import java.text.DecimalFormat;
import java.util.Date;

public class BoletoBancario {
	int bbnbanc;
	int bbnmoed;
	Date bbdvenc;
	float bbydoc;
	int bbncart;
	String bbcconv;
	int bbcnsnm;	
	
	public BoletoBancario(int bbnbanc, int bbnmoed, Date bbdvenc,
			float bbydoc, int bbncart, String bbcconv, int bbcnsnm) {
		this.bbnbanc = bbnbanc;
		this.bbnmoed = bbnmoed;
		this.bbdvenc = bbdvenc;
		this.bbydoc = bbydoc;
		this.bbncart = bbncart;
		this.bbcconv = bbcconv;
		this.bbcnsnm = bbcnsnm;
	}
	
	private long getDia(Date dia){
		return dia.getTime() / 0x5265c00L;
	}
	
	private long getDia(){
		return getDia((new Date(97, 10 - 1, 7)));
	}

	public String fatorVenc() {
		String s = "0000";
		try { 
			int l = new Long(getDia(bbdvenc) - getDia()).intValue();
			if (l > 999 && l < 10000)
				s = ControleBoleto.strZero(String.valueOf(l), 4);
		} catch (Throwable ex) {
		}
		return s;
	}

	public String getCodgBarras() throws Throwable {
		String s = 
				ControleBoleto.strZero("" + bbnbanc, 3) + bbnmoed
				+ fatorVenc()
				+ ControleBoleto.strZero(new DecimalFormat("0").format(100 * bbydoc), 10) + bbncart
				+ ControleBoleto.strZero(bbcconv, 6) + '9'
				+ ControleBoleto.strZero("" + bbcnsnm, 17);

		return s.substring(0, 4) + modulo11(s) + s.substring(4);
	}

	public String getLinDigt() throws Throwable {
		String cb = getCodgBarras();
		String campo1 = cb.substring(0, 4) + cb.substring(19, 24);
		String campo2 = cb.substring(24, 34);
		String campo3 = cb.substring(34, 44);
		return 
				campo1 + modulo10(campo1) + 
				campo2 + modulo10(campo2) + 
				campo3 + modulo10(campo3) + 
				cb.substring(4, 5) + // campo4
				cb.substring(5, 19);// campo5
	}
	
	public String getLinDigtFormt()throws Throwable{
        String ld = getLinDigt();
        return  ld.substring(0,5)+"."+ld.substring(5,10)+" "+                //campo 1
                ld.substring(10,15)+"."+ld.substring(15,21)+" "+             //campo 2
                ld.substring(21,26)+"."+ld.substring(26,32)+" "+             //campo 3
                ld.substring(32,33)+" "+ //campo 4
                ld.substring(33);        //campo 5
    } 
	
	public String getNossoNumero(){
		String nnum = null;
		try {
			nnum = "9"+ControleBoleto.strZero("" + bbcnsnm,17);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return nnum + "-" + ControleBoleto.modulo11(nnum);
	}
	
	public static int modulo11(String num){
	       int
	       dv=0,//digito verificador
	       i,
	       im=2//ndice de multipicao
	       ;

	       for(i=num.length()-1;i>=0;i--)
	       {
	         dv+=(num.charAt(i)-'0')*im;
	         if (++im>9)im=2;
	       }
	       dv%=11;
	       return (dv>=2 && dv<=9)?11-dv:1;
    }
	public static int nnModulo11(String num){
	       int
	       dv=0,//digito verificador
	       i,
	       im=2//ndice de multipicao
	       ;

	       for(i=num.length()-1;i>=0;i--)
	       {
	         dv+=(num.charAt(i)-'0')*im;
	         if (++im>9)im=2;
	       }
	       dv = 11 - (dv % 11);
	       return (dv > 9)? 0 : dv ;
	    }
    //para clculos dos dgitos verificadores dos campos 1, 2 e 3
    public static int modulo10(String num){
       int
       dv=0,dc,//digito verificador
       i,
       im=1//ndice de multipicao
       ;

       for(i=num.length()-1;i>=0;i--)
       {
         dc=(num.charAt(i)-'0')*(im+1);
         dv+=(dc<10?dc:(dc/10 + dc%10));
         im=(im+1)%2;
       }
       dv%=10;
       return (dv==0?0:10-dv);
    }
}
