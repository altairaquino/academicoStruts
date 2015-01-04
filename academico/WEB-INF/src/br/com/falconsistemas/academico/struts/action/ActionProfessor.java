package br.com.falconsistemas.academico.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import br.com.falconsistemas.academico.struts.bean.BeanProfessor;
import br.com.falconsistemas.academico.struts.form.FormProfessor;
import br.com.falconsistemas.academico.struts.model.ModelProfessor;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionProfessor extends DispatchAction {

	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		fw.setPath("/professorCadastro.do");
		
		request.setAttribute("ls_professor", ModelProfessor.getInstance().getProfessores());
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionMessages erros = new ActionMessages();
		FormProfessor formProfessor = (FormProfessor)form;
		BeanProfessor beanProfessor = new BeanProfessor();
		BeanUtils.copyProperties(beanProfessor, formProfessor);		
		
		try {
			
			if (!ValidaObjeto.validaString(beanProfessor.getPfcnome())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome do Professor é obrigatório."));
			}else{
				if (beanProfessor.getPfcnome().length() < 5){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","O nome do professor deve ter pelo menos 5 carateres."));
				}else{
					ModelProfessor.getInstance().inserir(beanProfessor);
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Professor Cadastrado com sucesso."));
					request.getSession().removeAttribute("formProfessor");
				}				
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		saveErrors(request, erros);
		
		
		
		return lista(mapping, form, request, response);
	}

}
