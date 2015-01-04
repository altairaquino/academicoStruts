package br.com.falconsistemas.academico.utils;

import java.util.Date;

public class ControleBoleto{
    
	public static int modulo11(String num){
       int
       dv=0,//digito verificador
       i,
       im=2//índice de multipicação
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
	       im=2//índice de multipicação
	       ;

	       for(i=num.length()-1;i>=0;i--)
	       {
	         dv+=(num.charAt(i)-'0')*im;
	         if (++im>9)im=2;
	       }
	       dv = 11 - (dv % 11);
	       return (dv > 9)? 0 : dv ;
	    }
    //para cálculos dos dígitos verificadores dos campos 1, 2 e 3
    public static int modulo10(String num){
       int
       dv=0,dc,//digito verificador
       i,
       im=1//índice de multipicação
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
    public static String strZero(String snum, int i)
        throws Throwable
    {
        String s1 = "0";
        if(snum == null)
            snum = "";
        snum = snum.trim();
        if(snum.length() > i)
            throw new Throwable("O valor " + snum + " tem mais de " + i +
			" d\355gitos. N\343o foi poss\355vel alinhar.");
        s1 = snum;
        for(int j = 0; j < i - snum.length(); j++)
            s1 = "0" + s1;

        return s1;
    }
	@SuppressWarnings("deprecation")
	public static String calculaFatorVencimento(Date DateVenc)
    {
        String s = "0000";
        try
        {
			int l = new  Long(
					(  DateVenc.getTime() / 0x5265c00L)
					 -(new Date(97,10-1,7).getTime() / 0x5265c00L)
					).intValue();
			if(l > 999 && l < 10000)
				s = strZero(String.valueOf(l), 4);
        }
        catch(Throwable ex) { }
        return s;
    }
    public static String formatarValor(double d, int i)
        throws Throwable
    {
        boolean flag = false;
        if(d < 0.0D)
        {
            flag = true;
            d *= -1D;
        }
        double d1 = d * Math.pow(10D, i) + 1.0D / Math.pow(10D, i + 4);
        StringBuffer stringbuffer;
        for(stringbuffer = new StringBuffer(String.valueOf((long)d1)); stringbuffer.length() < i + 1; stringbuffer.insert(0, '0'));
        int j = stringbuffer.length();
        if(i > 0)
            stringbuffer.insert(j - i, ',');
        for(int k = j - i - 3; k > 0; k -= 3)
            stringbuffer.insert(k, '.');

        if(flag)
            stringbuffer.insert(0, "-");
        return stringbuffer.toString();
    }
    public static String tirarPontos(String s)
        throws Throwable
    {
        if(s == null)
            s = "";
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            int k = s.indexOf(".");
            if(k < 0)
                break;
            s = s.substring(0, k) + s.substring(k + 1, s.length());
        }

        return s;
    }
    public static String trocaVirgulaPorPonto(String s)
        throws Throwable
    {
        if(s == null)
            s = "";
        return s.replace(',', '.');
    }
     public static String desformatarValor(double d, int i)
        throws Throwable
    {
        String s = "";
        s = tirarPontos(trocaVirgulaPorPonto(formatarValor(d, i)));
        return s;
    }
}