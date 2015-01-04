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

import br.com.falconsistemas.academico.struts.bean.BeanParcelaCursoSemestre;
import br.com.falconsistemas.academico.struts.form.FormParcelaCursoSemestre;
import br.com.falconsistemas.academico.struts.model.ModelParcelaCursoSemestre;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionParcelaCursoSemestre extends DispatchAction {

	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String cmncodg = request.getParameter("cmncodg");
		fw.setPath("/parcelaCursoSemestreLista.do");
		
		request.getSession().removeAttribute("formParcelaCursoSemestre");
		
		List<BeanParcelaCursoSemestre> l = ModelParcelaCursoSemestre.getInstance().getParcelasCursoSemestre(Integer.parseInt(cmncodg));
		
		if (l.isEmpty()){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhuma parcela cadastrada para este curso."));
			saveErrors(request, erros);
		}
		
		request.setAttribute("ls_parcelacursosemestre", l);
		
		return fw;
	}
	
	public ActionForward novaParcela(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		/*
		try {
			request.setAttribute("cursos", ModelCurso.getInstance().getCursos()); 
			request.setAttribute("turnos", ModelTurno.getInstance().getTurnos()); 
			request.setAttribute("unidades",ModelUnidadeEnsino.getInstance().getUnidadesEnsino());
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		fw.setPath("/parcelaCursoSemestreNovo.do");
						
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormParcelaCursoSemestre formParcelaCursoSemestre = (FormParcelaCursoSemestre)form;
		
		try {
			
			String cmncodg = request.getParameter("pcncodg");
			BeanParcelaCursoSemestre beanParcelaCursoSemestre = ModelParcelaCursoSemestre.getInstance().getParcelaCursoSemestre(Integer.parseInt(cmncodg));
			BeanUtils.copyProperties(formParcelaCursoSemestre, beanParcelaCursoSemestre);
			
			fw.setPath("/parcelaCursoSemestreEditar.do");
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return fw;
	}
	
	public ActionForward emitir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		
		
		String pcncodg = request.getParameter("pcncodg");
		String pcncgcm = request.getParameter("pcncgcm");
				
		if (pcncodg != null){
			ModelParcelaCursoSemestre.getInstance().emitirParcela(Integer.parseInt(pcncodg));
		}
		
		fw.setPath("/actionParcelaCursoSemestre.do?m=lista&cmncodg="+pcncgcm);
				
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormParcelaCursoSemestre formParcelaCursoSemestre = (FormParcelaCursoSemestre)form;
		BeanParcelaCursoSemestre beanParcelaCursoSemestre = new BeanParcelaCursoSemestre();
		BeanUtils.copyProperties(beanParcelaCursoSemestre, formParcelaCursoSemestre);
		
		if (!ValidaObjeto.validaData(beanParcelaCursoSemestre.getPcdvenc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Vencimento é inválida."));
		}
		if (!ValidaObjeto.validaData(beanParcelaCursoSemestre.getPcdjurs())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Juros é inválida."));
		}
		if (!ValidaObjeto.validaData(beanParcelaCursoSemestre.getPcdmult())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Vencimento é inválida."));
		}		
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw = this.novaParcela(mapping, form, request, response);
		}else{
		
			try {								
				ModelParcelaCursoSemestre.getInstance().inserir(beanParcelaCursoSemestre);
				fw.setPath("/actionParcelaCursoSemestre.do?m=lista&cmncodg="+formParcelaCursoSemestre.getPcncgcm());
				
				//limpa o form de cadastro
				formParcelaCursoSemestre.reset();
				request.getSession().removeAttribute("formParcelaCursoSemestre");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormParcelaCursoSemestre formParcelaCursoSemestre = (FormParcelaCursoSemestre)form;
		
		
		if (!ValidaObjeto.validaData(formParcelaCursoSemestre.getPcdvenc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Vencimento é inválida."));
		}
		if (!ValidaObjeto.validaData(formParcelaCursoSemestre.getPcdjurs())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Juros é inválida."));
		}
		if (!ValidaObjeto.validaData(formParcelaCursoSemestre.getPcdmult())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Vencimento é inválida."));
		}		
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw = this.novaParcela(mapping, form, request, response);
		}else{
		
			try {
				BeanParcelaCursoSemestre beanParcelaCursoSemestre = new BeanParcelaCursoSemestre();
				BeanUtils.copyProperties(beanParcelaCursoSemestre, formParcelaCursoSemestre);
				
				ModelParcelaCursoSemestre.getInstance().update(beanParcelaCursoSemestre);
				fw.setPath("/actionParcelaCursoSemestre.do?m=lista&cmncodg="+formParcelaCursoSemestre.getPcncgcm());
				
				//limpa o form de cadastro
				formParcelaCursoSemestre.reset();
				request.getSession().removeAttribute("formParcelaCursoSemestre");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fw;
	}

}
