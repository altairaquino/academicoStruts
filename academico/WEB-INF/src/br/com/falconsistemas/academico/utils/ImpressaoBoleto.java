package br.com.falconsistemas.academico.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ImpressaoBoleto {
	 public static String gerarBoleto(Map<String,Object> map){
	        String nomeArquivo = null;		
	        try {		
	            Integer ctncodg = (Integer)map.get("BLNCODG");
	            String path = (String)map.get("PATH");
	            String jasper = (String)map.get("JASPER");
	            JasperPrint impressao = JasperFillManager.fillReport(jasper, map);	            
	            //JasperViewer.viewReport(impressao,false);
	            nomeArquivo = ctncodg+"_"+new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date().getTime())+".pdf";
	            JasperExportManager.exportReportToPdfFile(impressao ,path+nomeArquivo);
	        }
	        catch(Exception exception){
	                exception.printStackTrace();
	        }
	        return nomeArquivo;
	    }
	 public static void main(String[] args){
		 
	 }
}
