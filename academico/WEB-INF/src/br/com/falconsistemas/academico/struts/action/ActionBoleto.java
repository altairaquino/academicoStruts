package br.com.falconsistemas.academico.struts.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import br.com.falconsistemas.academico.struts.bean.BeanBoleto;
import br.com.falconsistemas.academico.struts.bean.BeanCursoSemestre;
import br.com.falconsistemas.academico.struts.bean.BeanUsuario;
import br.com.falconsistemas.academico.struts.form.FormBoleto;
import br.com.falconsistemas.academico.struts.model.Banco;
import br.com.falconsistemas.academico.struts.model.ModelAluno;
import br.com.falconsistemas.academico.struts.model.ModelBoleto;
import br.com.falconsistemas.academico.struts.model.ModelCursoSemestre;
import br.com.falconsistemas.academico.struts.model.ModelOperacao;
import br.com.falconsistemas.academico.utils.ImpressaoBoleto;
import br.com.falconsistemas.academico.utils.Utils;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionBoleto extends DispatchAction {

	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		FormBoleto formBoleto = (FormBoleto)form;
		try {
			
			String blncodg = request.getParameter("blncodg");
			BeanBoleto beanBoleto = ModelBoleto.getInstance().getBoleto(Integer.parseInt(blncodg));
			BeanUtils.copyProperties(formBoleto, beanBoleto);
			
			fw.setPath("/boletoEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward pesquisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormBoleto formBoleto = (FormBoleto)form;
		
		try {
			
			String blnnnum = request.getParameter("blnnnum");
			
			blnnnum = blnnnum.replaceAll("^9[0-9]0+", "");
			
			if (!ValidaObjeto.validaInteiro(blnnnum)){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor de Entrada Invalido!"));
			}else{
				if (ModelBoleto.getInstance().getBoletoNossoNumero(Integer.parseInt(blnnnum)) == null){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Boleto não encontrado ou já pago!"));
				}
			}
						
			if (erros.isEmpty()){
				
				BeanBoleto boleto = ModelBoleto.getInstance().getBoletoNossoNumero(Integer.parseInt(blnnnum));
				BeanUtils.copyProperties(formBoleto, boleto);
				
				fw.setPath("/boletoBaixa2.do");
			}else{	
				saveErrors(request, erros);
				fw.setPath("/boletoPesquisa.do");
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return fw;		
	}
	
	public ActionForward desativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		
		try {
			
			String blncodg = request.getParameter("blncodg");
			BeanBoleto beanBoleto = ModelBoleto.getInstance().getBoleto(Integer.parseInt(blncodg));
			ModelBoleto.getInstance().dasativa(beanBoleto);
			
			fw.setPath("/actionBoleto.do?m=lista&alncodg="+beanBoleto.getBlncgal());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		FormBoleto formBoleto = (FormBoleto)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaData(formBoleto.getBldvenc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Vencimento é inválida."));
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formBoleto.getBlyvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor do boleto esta incorreto."));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/boletoEditar.do");
		}else{
			try {
				BeanBoleto boleto = new BeanBoleto();				
								
				BeanUtils.copyProperties(boleto, formBoleto);
				
				ModelBoleto.getInstance().update(boleto);
				
				request.getSession().removeAttribute("formBoleto");
				
				fw.setPath("/actionBoleto.do?m=lista&alncodg="+formBoleto.getBlncgal());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		final int operacao = 22; 
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String alncodg = request.getParameter("alncodg");
		fw.setPath("/boletoAlunoLista.do");
		
		BeanUsuario usuario = (BeanUsuario)request.getSession().getAttribute("usuario");
		
		boolean acessoOk = ModelOperacao.getInstance().usuarioTemAcessoOperacao(Integer.parseInt(usuario.getUsncodg()), operacao);
		
		if (acessoOk){
			request.getSession().setAttribute("baixaboleto", "baixaboleto");
		}else{
			request.getSession().removeAttribute("baixaboleto");
		}
		
		List<BeanBoleto> l = ModelBoleto.getInstance().getBoletosDoAluno(Integer.parseInt(alncodg));
		
		if (l.isEmpty()){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Não há boletos para este aluno."));
			saveErrors(request, erros);
		}else{
			request.setAttribute("soma", new DecimalFormat("#,##0.00;(#,##0.00)").format(ModelBoleto.getInstance().getSomaBoletos(Integer.parseInt(alncodg))));
		}
		
		request.setAttribute("ls_boletos", l);
		request.setAttribute("aluno", ModelAluno.getInstance().getAluno(Integer.parseInt(alncodg)));
		
		return fw;
	}
	
	public ActionForward baixar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormBoleto formBoleto = (FormBoleto)form;
		BeanBoleto beanBoleto = new BeanBoleto();
		
		BeanUtils.copyProperties(beanBoleto, formBoleto);
				
		try {
			ModelBoleto.getInstance().baixar(beanBoleto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fw.setPath("/actionBoleto.do?m=lista&alncodg="+formBoleto.getBlncgal());
		request.getSession().removeAttribute("formBoleto");
						
		return fw;
	}
	
	public ActionForward baixar2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormBoleto formBoleto = (FormBoleto)form;
		BeanBoleto beanBoleto = new BeanBoleto();
		
		BeanUtils.copyProperties(beanBoleto, formBoleto);
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formBoleto.getBlytari()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor da tarifa é Inválido."));
		}
		
		if (!ValidaObjeto.validaData(formBoleto.getBldpgto())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Vencimento esta incorreta!"));
		}
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/boletoBaixa2.do");
		}else{
			try {
				ModelBoleto.getInstance().baixar2(beanBoleto);
				request.getSession().removeAttribute("formBoleto");
				fw.setPath("/boletoPesquisa.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
						
		return fw;
	}
	
	public ActionForward montaBaixa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		ActionForward fw = new ActionForward();
		FormBoleto formBoleto = (FormBoleto)form;
		
		String blncodg = request.getParameter("blncodg");
		
		try {
			BeanBoleto beanBoleto = ModelBoleto.getInstance().getBoleto(Integer.parseInt(blncodg));
			BeanUtils.copyProperties(formBoleto, beanBoleto);
			
			BeanCursoSemestre cursoSemestre = ModelCursoSemestre.getInstance().getCursoSemestre(Integer.parseInt(formBoleto.getBlncgcm()));
			
			if (formBoleto.getBlncgtb().equals("1")){
			
				float multa = 0, desconto = 0, juro = 0;
				float percentualJuro = Float.parseFloat(Utils.converteFloatBR(cursoSemestre.getCmyjuro()));
				float percentualMulta = Float.parseFloat(Utils.converteFloatBR(cursoSemestre.getCmymult()));
				float percentualDesc = Float.parseFloat(Utils.converteFloatBR(cursoSemestre.getCmydesc()));
				float valor = Float.parseFloat(Utils.converteFloatBR(beanBoleto.getBlyvalr()));
				Date vencimento = Utils.strBRToDate(formBoleto.getBldvenc());
				
				Date hoje = new Date();
				
				if (vencimento.before(hoje)){
					int dias = (int)((hoje.getTime() - vencimento.getTime())/86400000);
					multa = (valor * percentualMulta / 100);
					juro = (valor * percentualJuro / 3000 * dias);
				}else{
					desconto = (valor * percentualDesc / 100);
				}
				
				formBoleto.setBlycobr(Utils.floatToStrBR(valor - desconto + multa + juro));
				formBoleto.setBlydesc(Utils.floatToStrBR(desconto));
				formBoleto.setBlyjurs(Utils.floatToStrBR(juro));
				formBoleto.setBlymult(Utils.floatToStrBR(multa));
				
				request.setAttribute("desconto", Utils.floatToStrBR(percentualDesc));
				request.setAttribute("multa", Utils.floatToStrBR(percentualMulta));
				request.setAttribute("juros", Utils.floatToStrBR(percentualJuro));
				
			}else{
				formBoleto.setBlycobr(formBoleto.getBlyvalr());
			}			
			
			request.setAttribute("boleto", beanBoleto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		fw.setPath("/boletoBaixa.do");		
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		/*
		ActionMessages erros = new ActionMessages();
		FormCursoSemestre formCursoSemestre = (FormCursoSemestre)form;
		BeanCursoSemestre beanCursoSemestre = new BeanCursoSemestre();
		BeanUtils.copyProperties(beanCursoSemestre, formCursoSemestre);
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(beanCursoSemestre.getCmyvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor Mensal do Curso Inválido."));
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(beanCursoSemestre.getCmyjuro()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor dos Juros Inválido."));
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(beanCursoSemestre.getCmydesc()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor do Desconto Inválido."));
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(beanCursoSemestre.getCmymult()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor da Multa Inválida."));
		}
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw = this.novo(mapping, form, request, response);
		}else{
		
			try {								
				ModelCursoSemestre.getInstance().inserir(beanCursoSemestre);
				fw.setPath("/actionCursoSemestre.do?m=lista&smncodg="+formCursoSemestre.getCmncgsm());
				
				//limpa o form de cadastro
				request.getSession().removeAttribute("formCursoSemestre");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		return fw;
	}
	
	
	
	public ActionForward gerarCarne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		final int operacao = 23; 
		
		ActionForward fw = new ActionForward();
		fw.setPath("/gerarCarneAlunos.do");
		
		BeanUsuario usuario = (BeanUsuario)request.getSession().getAttribute("usuario");
		
		boolean acessoOk = ModelOperacao.getInstance().usuarioTemAcessoOperacao(Integer.parseInt(usuario.getUsncodg()), operacao);
		request.getSession().setAttribute("resumoParcelas", ModelCursoSemestre.getInstance().getResumoParcelas());
		if (acessoOk){
			request.getSession().setAttribute("baixaboleto", "baixaboleto");
		}else{
			request.getSession().removeAttribute("baixaboleto");
		}
		
		return fw;
	}
	
	
	public ActionForward imprimirCarne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			
			Map<String,Object> map= new HashMap<String,Object>();
			
			try{   	   			
				String path = request.getSession().getServletContext().getRealPath("/");
				
				map.put("REPORT_CONNECTION",Banco.getConnection());
		        map.put("PATH", path + "boletos/");
		        map.put("JASPER",path + "jasper/BoletoCaixaCarneTodos.jasper");
	            map.put("IMG_CAIXA", path + "imagens/caixa_img.jpg");
	            map.put("IMG_LOGO", path + "imagens/logo_iesl_peq.jpg");
				String caminho = ImpressaoBoleto.gerarBoleto(map);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition","attachment; filename=\"carne_alunos.pdf\"");
			    //response.setHeader("Cache-Control", "no-cache");
				fw.setPath("/boletos/"+caminho);				
		
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward imprimirCarneAluno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			
			String aluno = request.getParameter("aluno");
			
			Map<String,Object> map= new HashMap<String,Object>();
			
			try{
				String path = request.getSession().getServletContext().getRealPath("/");
				
				map.put("REPORT_CONNECTION",Banco.getConnection());
		        map.put("PATH", path + "boletos/");
		        map.put("JASPER",path + "jasper/BoletoCaixaCarneAluno.jasper");
	            map.put("IMG_CAIXA", path + "imagens/caixa_img.jpg");
	            map.put("IMG_LOGO", path + "imagens/logo_iesl_peq.jpg");
	            map.put("ALNCODG", new Integer(aluno));
				String caminho = ImpressaoBoleto.gerarBoleto(map);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition","attachment; filename=\"carne_alunos.pdf\"");
			    fw.setPath("/boletos/"+caminho);				
		
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward imprimirCarneCursoSemestre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			
			String cmncodg = request.getParameter("cmncodg");
			
			Map<String,Object> map= new HashMap<String,Object>();
			
			try{
				String path = request.getSession().getServletContext().getRealPath("/");
				
				map.put("REPORT_CONNECTION",Banco.getConnection());
		        map.put("PATH", path + "boletos/");
		        map.put("JASPER",path + "jasper/BoletoCaixaCarneCursoSemestre.jasper");
	            map.put("IMG_CAIXA", path + "imagens/caixa_img.jpg");
	            map.put("IMG_LOGO", path + "imagens/logo_iesl_peq.jpg");
	            map.put("CMNCODG", new Integer(cmncodg));
				String caminho = ImpressaoBoleto.gerarBoleto(map);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition","attachment; filename=\"carne_alunos.pdf\"");
			    fw.setPath("/boletos/"+caminho);				
		
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}
	
	
	
	
	
	public ActionForward imprimir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		String blncodg = request.getParameter("blncodg");
		
		try {			
			
			Map<String,Object> map= new HashMap<String,Object>();
			
			try{   	   			
				String path = request.getSession().getServletContext().getRealPath("/");
				
				map.put("REPORT_CONNECTION",Banco.getConnection());
				map.put("BLNCODG",new Integer(blncodg));
		        map.put("PATH", path + "boletos/");
		        map.put("JASPER",path + "jasper/BoletoCaixa.jasper");
	            map.put("IMG_CAIXA", path + "imagens/caixa_img.jpg");
				String caminho = ImpressaoBoleto.gerarBoleto(map);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition","attachment; filename=\"boleto_mensalidade.pdf\"");
			    //response.setHeader("Cache-Control", "no-cache");
				fw.setPath("/boletos/"+caminho);				
		
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}

}
