package br.com.falconsistemas.academico.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.falconsistemas.academico.struts.model.ModelSemestre;
import br.com.falconsistemas.academico.utils.GeraRelatorio;

public class ActionSemestre extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		fw.setPath("/semestreLista.do");
		
		request.setAttribute("ls_semestre", ModelSemestre.getInstance().getSemestres());
		
		return fw;
	}
	
	public ActionForward opcoesSemestre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {
			
			String smncodg = request.getParameter("smncodg");
			fw.setPath("/semestrePage.do");
			request.getSession().setAttribute("semestre",ModelSemestre.getInstance().getSemestre(Integer.parseInt(smncodg)));
			
			request.getSession().setAttribute("semestreaberto", "semestreaberto");
			/*
			if (ModelSemestre.getInstance().semestreEstaAberto(Integer.parseInt(smncodg))){
				request.getSession().setAttribute("semestreaberto", "semestreaberto");
			}else{
				request.getSession().removeAttribute("semestreaberto");
			}
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward relatorioAlunos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			
			String smncodg = request.getParameter("smncodg");
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("SEMESTRE", new Integer(smncodg));
			map.put("REPORT_NAME", "alunos_semestre");
			
			GeraRelatorio.geracao(request, response, map, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ActionForward relatorioFinanceiro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			
			String smncodg = request.getParameter("smncodg");
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("SMNCODG", new Integer(smncodg));
			map.put("REPORT_NAME", "resumo_finaceiro_semestre");
			
			GeraRelatorio.geracao(request, response, map, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ActionForward relatorioFinanceiroPend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			
			String smncodg = request.getParameter("smncodg");
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("SMNCODG", new Integer(smncodg));
			map.put("REPORT_NAME", "lista_inadimplencia");
			
			GeraRelatorio.geracao(request, response, map, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
