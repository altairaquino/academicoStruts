package br.com.falconsistemas.academico.struts.action;

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

import br.com.falconsistemas.academico.struts.bean.BeanCursoSemestre;
import br.com.falconsistemas.academico.struts.bean.BeanTurma;
import br.com.falconsistemas.academico.struts.form.FormTurma;
import br.com.falconsistemas.academico.struts.model.Banco;
import br.com.falconsistemas.academico.struts.model.ModelDisciplina;
import br.com.falconsistemas.academico.struts.model.ModelProfessor;
import br.com.falconsistemas.academico.struts.model.ModelTurma;
import br.com.falconsistemas.academico.utils.ImpressaoBoleto;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionTurma extends DispatchAction {

	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String cmncodg = request.getParameter("cmncodg");
		fw.setPath("/turmaLista.do");
		
		request.getSession().removeAttribute("formTurma");
		
		List<BeanTurma> l = ModelTurma.getInstance().getTurmasCursoSemestre(Integer.parseInt(cmncodg));
		
		if (l.isEmpty()){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhuma turma cadastrada para este curso."));
			saveErrors(request, erros);
		}
		
		request.setAttribute("ls_turma", l);
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormTurma formTurma = (FormTurma)form;
		
		BeanTurma beanTurma = new BeanTurma();
		BeanUtils.copyProperties(beanTurma, formTurma);
		
		try {
			ModelTurma.getInstance().update(beanTurma);
			request.getSession().removeAttribute("formTurma");
		} catch (Exception e) {
			e.printStackTrace();
		}		
				
		fw.setPath("/actionTurma.do?m=lista&cmncodg="+formTurma.getTmncgcm());
		
		return fw;
	}
	
		
	public ActionForward novaTurma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanCursoSemestre beanCursoSemestre = (BeanCursoSemestre)request.getSession().getAttribute("cursosemestre");
		
		try {
			request.setAttribute("ls_disciplina", ModelDisciplina.getInstance().getDisciplinasDoCurso(Integer.parseInt(beanCursoSemestre.getCmncgcs()))); 
			request.setAttribute("ls_professor", ModelProfessor.getInstance().getProfessores()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		fw.setPath("/turmaNova.do");
						
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormTurma formTurma = (FormTurma)form;
		
		String tmncodg = request.getParameter("tmncodg");
		
		BeanTurma beanTurma = ModelTurma.getInstance().getTurma(Integer.parseInt(tmncodg));
		
		request.setAttribute("ls_disciplina", ModelDisciplina.getInstance().getDisciplinasDoCurso(Integer.parseInt(beanTurma.getTmncgcs()))); 
		request.setAttribute("ls_professor", ModelProfessor.getInstance().getProfessores());
		
		BeanUtils.copyProperties(formTurma, beanTurma);
				
		fw.setPath("/turmaEditar.do");
		
		return fw;
	}
	
	public ActionForward importar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		String cmncodg = request.getParameter("cmncodg");
		
		ModelTurma.getInstance().importaTurmasCursoSemestre(Integer.parseInt(cmncodg));
				
		fw.setPath("/actionTurma.do?m=lista&cmncodg="+cmncodg);
		
		return fw;
	}
	
		
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormTurma formTurma = (FormTurma)form;
		BeanTurma beanTurma = new BeanTurma();
		BeanUtils.copyProperties(beanTurma, formTurma);

		if (!ValidaObjeto.validaString(beanTurma.getTmnperi())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Período da Turma é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(beanTurma.getTmnmax())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Máximo de aluno da turma é obrigatório."));
		}
				
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw = this.novaTurma(mapping, form, request, response);
		}else{
		
			try {								
				ModelTurma.getInstance().inserir(beanTurma);
				fw.setPath("/actionTurma.do?m=lista&cmncodg="+formTurma.getTmncgcm());
				
				//limpa o form de cadastro		
				request.getSession().removeAttribute("formTurma");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return fw;
	}	
	
	public ActionForward relatorio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		String tmncodg = request.getParameter("tmncodg");
		
		try {			
			
			Map<String,Object> map= new HashMap<String,Object>();
			
			
			String path = request.getSession().getServletContext().getRealPath("/");
			
			map.put("REPORT_CONNECTION",Banco.getConnection());
			map.put("TURMA",new Integer(tmncodg));
			map.put("IMAGE_PATH", path+"/imagens/");
			map.put("PATH", path + "relatorios/");
	        map.put("JASPER",path + "jasper/diario_classe.jasper");
	        String caminho = ImpressaoBoleto.gerarBoleto(map);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment; filename=\"diario_classe.pdf\"");
		    response.setHeader("Cache-Control", "no-cache");
		    
		    fw.setPath("/relatorios/"+caminho);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward recibo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		String tmncodg = request.getParameter("tmncodg");
		
		try {			
			
			Map<String,Object> map= new HashMap<String,Object>();
			
			
			String path = request.getSession().getServletContext().getRealPath("/");
			
			map.put("REPORT_CONNECTION",Banco.getConnection());
			map.put("TURMA",new Integer(tmncodg));
			map.put("IMAGE_PATH", path+"/imagens/");
			map.put("PATH", path + "relatorios/");
	        map.put("JASPER",path + "jasper/recibo.jasper");
	        String caminho = ImpressaoBoleto.gerarBoleto(map);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment; filename=\"recibo_pagto.pdf\"");
		    response.setHeader("Cache-Control", "no-cache");
		    
		    fw.setPath("/relatorios/"+caminho);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward historico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		String alncodg = request.getParameter("aluno");
		
		try {			
			
			Map<String,Object> map= new HashMap<String,Object>();
			
			
			String path = request.getSession().getServletContext().getRealPath("/");
			
			map.put("REPORT_CONNECTION",Banco.getConnection());
			map.put("ALUNO",new Integer(alncodg));
			map.put("IMAGE_PATH", path+"/imagens/");
			map.put("PATH", path + "relatorios/");
	        map.put("JASPER",path + "jasper/historico_escolar.jasper");
	        String caminho = ImpressaoBoleto.gerarBoleto(map);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment; filename=\"historico_escolar.pdf\"");
		    response.setHeader("Cache-Control", "no-cache");
		    
		    fw.setPath("/relatorios/"+caminho);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}

}
