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
import br.com.falconsistemas.academico.struts.bean.BeanMatricula;
import br.com.falconsistemas.academico.struts.bean.BeanUsuario;
import br.com.falconsistemas.academico.struts.form.FormMatricula;
import br.com.falconsistemas.academico.struts.model.ModelAluno;
import br.com.falconsistemas.academico.struts.model.ModelCursoSemestre;
import br.com.falconsistemas.academico.struts.model.ModelMatricula;
import br.com.falconsistemas.academico.struts.model.ModelTipoIngresso;
import br.com.falconsistemas.academico.struts.model.ModelTurma;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionMatricula extends DispatchAction {

	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		//ActionMessages erros = new ActionMessages();		
		
		return fw;
	}
	
	public ActionForward nova(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMatricula formMatricula = (FormMatricula)form;
		String alccpf = request.getParameter("alccpf");
		
		try {
			
			if (alccpf != null){
				BeanAluno aluno = ModelAluno.getInstance().getAlunosPorNomeCpf(alccpf).get(0);
				request.getSession().setAttribute("aluno_mat", aluno);
				formMatricula.setMtncgal(aluno.getAlncodg());
				formMatricula.setMtcnmal(aluno.getAlcnome());
				formMatricula.setMtccpf(aluno.getAlccpf());			
			}
			
			request.setAttribute("ls_cursos", ModelCursoSemestre.getInstance().getCursosDoSemestreMatricula()); 
			request.setAttribute("ls_tipoingresso", ModelTipoIngresso.getInstance().getTiposIngresso());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fw.setPath("/matriculaAluno.do");
						
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMatricula formMatricula = (FormMatricula)form;
		BeanMatricula beanMatricula = new BeanMatricula();
		
		BeanUtils.copyProperties(beanMatricula, formMatricula);
		
		try {
			
			BeanUsuario beanUsuario = (BeanUsuario)request.getSession().getAttribute("usuario");
			
			if (beanUsuario != null){
				beanMatricula.setMtncgus(beanUsuario.getUsncodg());
			}
			
			ModelMatricula.getInstance().inserir(beanMatricula);
			request.getSession().setAttribute("curso_mat", ModelCursoSemestre.getInstance().getCursoSemestre(Integer.parseInt(beanMatricula.getMtncgcm())));
			request.getSession().setAttribute("aluno_mat", ModelAluno.getInstance().getAluno(Integer.parseInt(beanMatricula.getMtncgal())));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fw.setPath("/matriculaSucesso.do");
						
		return fw;
	}
	
	public ActionForward pesquisaMatricula(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormMatricula formMatricula = (FormMatricula) form;
		
		if (!ValidaObjeto.validaString(formMatricula.getMtcnmal())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
		}else{
			if (formMatricula.getMtcnmal().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
			}
		}
		
		if (erros.isEmpty()){
			List<BeanAluno> listaAluno = ModelAluno.getInstance().getAlunosPorNomeCpfSemMatricula(formMatricula.getMtcnmal());
			if (listaAluno.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno encontrado!"));
			}else{
				request.setAttribute("ls_alunos", listaAluno);
			}
		}
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoPesquisaMatricula.do");
		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaMatriculaModulo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormMatricula formMatricula = (FormMatricula) form;
		
		if (!ValidaObjeto.validaString(formMatricula.getMtcnmal())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
		}else{
			if (formMatricula.getMtcnmal().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
			}
		}
		
		if (erros.isEmpty()){
			List<BeanAluno> listaAluno = ModelAluno.getInstance().getAlunosPorNomeCpf(formMatricula.getMtcnmal());
			if (listaAluno.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno encontrado!"));
			}else{
				request.setAttribute("ls_alunos", listaAluno);
			}
		}
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoPesquisaMatriculaModulo.do");
		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward montaMatricula(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String alncodg = request.getParameter("alncodg");
		BeanMatricula matricula = ModelMatricula.getInstance().matriculaDoAluno(Integer.parseInt(alncodg));
		
		if (matricula == null){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Aluno ainda não matriculado!"));
			fw.setPath("/alunoPesquisaMatricula.do");
		}else{
			request.getSession().setAttribute("turmas_mat", ModelTurma.getInstance().getTurmasParaMatricula(Integer.parseInt(alncodg)));
			request.getSession().setAttribute("turmas_mat_temp", ModelTurma.getInstance().getTurmasParaMatriculaTemp(Integer.parseInt(alncodg)));
			request.getSession().setAttribute("matricula", matricula);
			fw.setPath("/matriculaPage.do");
		}
		
		return fw;
	}
		
	public ActionForward matricular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();		
		
		String tmncodg = request.getParameter("tmncodg");		
		String mtncodg = request.getParameter("mtncodg");
		
		BeanMatricula beanMatricula = (BeanMatricula)request.getSession().getAttribute("matricula");
		
		try {
			if (tmncodg != null){
				ModelMatricula.getInstance().matricular(Integer.parseInt(mtncodg),Integer.parseInt(tmncodg));
			}
			request.getSession().setAttribute("turmas_mat", ModelTurma.getInstance().getTurmasParaMatricula(Integer.parseInt(beanMatricula.getMtncgal())));
			request.getSession().setAttribute("turmas_mat_temp", ModelTurma.getInstance().getTurmasParaMatriculaTemp(Integer.parseInt(beanMatricula.getMtncgal())));
			fw.setPath("/matriculaPage.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
								
		return fw;
	}
	
	public ActionForward remover(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();		
		
		String tmncodg = request.getParameter("tmncodg2");		
		String mtncodg = request.getParameter("mtncodg");
		
		BeanMatricula beanMatricula = (BeanMatricula)request.getSession().getAttribute("matricula");
		
		try {
			if (tmncodg != null){
				ModelMatricula.getInstance().remover(Integer.parseInt(mtncodg),Integer.parseInt(tmncodg));
			}
			request.getSession().setAttribute("turmas_mat", ModelTurma.getInstance().getTurmasParaMatricula(Integer.parseInt(beanMatricula.getMtncgal())));
			request.getSession().setAttribute("turmas_mat_temp", ModelTurma.getInstance().getTurmasParaMatriculaTemp(Integer.parseInt(beanMatricula.getMtncgal())));
			fw.setPath("/matriculaPage.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
								
		return fw;
	}	

}
