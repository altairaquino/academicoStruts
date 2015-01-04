package br.com.falconsistemas.academico.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import br.com.falconsistemas.academico.struts.bean.BeanAluno;
import br.com.falconsistemas.academico.struts.bean.BeanCursoSemestre;
import br.com.falconsistemas.academico.struts.form.FormCursoSemestre;
import br.com.falconsistemas.academico.struts.model.ModelCurso;
import br.com.falconsistemas.academico.struts.model.ModelCursoSemestre;
import br.com.falconsistemas.academico.struts.model.ModelTurno;
import br.com.falconsistemas.academico.struts.model.ModelUnidadeEnsino;
import br.com.falconsistemas.academico.utils.Utils;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionCursoSemestre extends DispatchAction {

	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String smncodg = request.getParameter("smncodg");
		fw.setPath("/cursoSemestreLista.do");
		
		request.getSession().removeAttribute("formCursoSemestre");
		
		List<BeanCursoSemestre> l = ModelCursoSemestre.getInstance().getCursosDoSemestre(Integer.parseInt(smncodg));
		
		if (l.isEmpty()){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum curso do semestre cadastrado!"));
			saveErrors(request, erros);
		}
		
		request.setAttribute("ls_cursosemestre", l);
		
		return fw;
	}
	
	
	public ActionForward listaAlunos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String cmncodg = request.getParameter("cmncodg");
		fw.setPath("/cursoSemestreListaAlunos.do");
		
		List<BeanAluno> l = ModelCursoSemestre.getInstance().getListaAlunosMatriculados(Integer.parseInt(cmncodg));
		
		if (l.isEmpty()){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno matriculado neste curso!"));
			saveErrors(request, erros);
		}
		
		request.setAttribute("ls_aluno", l);
		
		return fw;
	}
	
	public ActionForward novoCurso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		try {
			request.setAttribute("cursos", ModelCurso.getInstance().getCursos()); 
			request.setAttribute("turnos", ModelTurno.getInstance().getTurnos()); 
			request.setAttribute("unidades",ModelUnidadeEnsino.getInstance().getUnidadesEnsino());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fw.setPath("/cursoSemestreNovo.do");
						
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormCursoSemestre formCursoSemestre = (FormCursoSemestre)form;
		
		String cmncodg = request.getParameter("cmncodg");
		
		BeanCursoSemestre beanCursoSemestre = ModelCursoSemestre.getInstance().getCursoSemestre(Integer.parseInt(cmncodg));
		
		request.setAttribute("cursos", ModelCurso.getInstance().getCursos()); 
		request.setAttribute("turnos", ModelTurno.getInstance().getTurnos()); 
		request.setAttribute("unidades",ModelUnidadeEnsino.getInstance().getUnidadesEnsino());
		
		BeanUtils.copyProperties(formCursoSemestre, beanCursoSemestre);
				
		fw.setPath("/cursoSemestreEditar.do");
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormCursoSemestre formCursoSemestre = (FormCursoSemestre)form;
		
		BeanCursoSemestre beanCursoSemestre = new BeanCursoSemestre();
		BeanUtils.copyProperties(beanCursoSemestre, formCursoSemestre);
		
		try {
			ModelCursoSemestre.getInstance().update(beanCursoSemestre);
			request.getSession().removeAttribute("formCursoSemestre");
		} catch (Exception e) {
			e.printStackTrace();
		}		
				
		fw.setPath("/actionCursoSemestre.do?m=opcoesCursoSemestre&cmncodg="+formCursoSemestre.getCmncodg());
		
		return fw;
	}
	
	public ActionForward opcoesCursoSemestre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		String cmncodg = request.getParameter("cmncodg");
				
		fw.setPath("/cursoSemestrePage.do");
		
		request.getSession().setAttribute("cursosemestre", ModelCursoSemestre.getInstance().getCursoSemestre(Integer.parseInt(cmncodg)));
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
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
			fw = this.novoCurso(mapping, form, request, response);
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
		
		return fw;
	}
	

}
