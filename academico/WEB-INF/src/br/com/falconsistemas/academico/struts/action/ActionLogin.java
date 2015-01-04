package br.com.falconsistemas.academico.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import br.com.falconsistemas.academico.struts.bean.BeanUsuario;
import br.com.falconsistemas.academico.struts.form.FormLogin;
import br.com.falconsistemas.academico.struts.model.ModelEntidade;
import br.com.falconsistemas.academico.struts.model.ModelOperacao;
import br.com.falconsistemas.academico.struts.model.ModelUsuario;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionLogin extends DispatchAction {

	
	public ActionForward autenticaUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormLogin formLogin = (FormLogin)form;
		
		String senha = formLogin.getSenha();
		String login = formLogin.getLogin();
		
		if (!ValidaObjeto.validaString(senha) && !ValidaObjeto.validaString(login)){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Login ou senha Invalidos ou usuário inabilitado."));
		}
		
		if (erros.isEmpty()){
			if(ModelUsuario.getInstance().autenticaUsuario(login, senha)){
				BeanUsuario usuario = ModelUsuario.getInstance().getBeanUsuarioPorLogin(login);
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("entidade", ModelEntidade.getInstance().getEntidade());
				request.getSession().setAttribute("menu", ModelOperacao.getInstance().getMenu(Integer.parseInt(usuario.getUsncodg())));
				//limpa os dados do formLogin
				request.getSession().removeAttribute("formLogin");
				
				fw.setPath("/home.do");
			}else{
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Login ou senha Invalidos ou usuário inabilitado."));
				saveErrors(request, erros);
				fw.setPath("/login.do");
			}
		}else{
			saveErrors(request, erros);
			fw.setPath("/login.do");
		}	
		
		
		return fw;
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		fw.setPath("/login.do");
		
		request.getSession().invalidate();
		
		return fw;
	}
	
	

}
