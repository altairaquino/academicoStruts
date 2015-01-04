package br.com.falconsistemas.academico.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import br.com.falconsistemas.academico.struts.model.Banco;

public class GeraRelatorio {
	
	public static void geracao(HttpServletRequest request, HttpServletResponse response, Map<Object, Object> map, boolean exporta){
		
		String path = request.getSession().getServletContext().getRealPath("/");		
		
		map.put("IMAGE_PATH",path + "imagens/");
		map.put("REPORT_CONNECTION", Banco.getConnection());
		
		String jasperName = map.get("REPORT_NAME").toString();
		
		String dia = new SimpleDateFormat("ddMMyyyyhhmm",new Locale("pt","BR")).format(new Date());
		
		JasperReport jasperReport;
		try {
			
			jasperReport = (JasperReport) JRLoader.loadObject(path+"jasper/"+jasperName+".jasper");
			byte[] pdfRelatorio = JasperRunManager.runReportToPdf(jasperReport, map);
			
			response.setContentType("application/pdf");    
			response.setHeader("Cache-Control", "no-store");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment; filename="+(jasperName+dia)+".pdf");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();    
			servletOutputStream.write(pdfRelatorio);
			servletOutputStream.flush();
			servletOutputStream.close();
			
			if (exporta){
				JasperPrint impressao = JasperFillManager.fillReport(path+"jasper/"+jasperName+".jasper", map);
				JasperExportManager.exportReportToPdfFile(impressao,path+"relatorios/" + (jasperName+dia) + ".pdf");
			}
			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}

}
