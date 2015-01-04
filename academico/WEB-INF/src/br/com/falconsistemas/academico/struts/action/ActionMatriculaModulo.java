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

import br.com.falconsistemas.academico.struts.bean.BeanMatricula;
import br.com.falconsistemas.academico.struts.bean.BeanMatriculaModulo;
import br.com.falconsistemas.academico.struts.bean.BeanTurma;
import br.com.falconsistemas.academico.struts.form.FormMatriculaModulo;
import br.com.falconsistemas.academico.struts.form.FormTurma;
import br.com.falconsistemas.academico.struts.model.ModelDisciplina;
import br.com.falconsistemas.academico.struts.model.ModelMatricula;
import br.com.falconsistemas.academico.struts.model.ModelMatriculaModulo;
import br.com.falconsistemas.academico.struts.model.ModelModulo;
import br.com.falconsistemas.academico.struts.model.ModelProfessor;
import br.com.falconsistemas.academico.struts.model.ModelSemestre;
import br.com.falconsistemas.academico.struts.model.ModelTurma;

public class ActionMatriculaModulo extends DispatchAction {

	
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
	
		
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMatriculaModulo formMatriculaModulo = (FormMatriculaModulo)form;
		try {
			String alncodg = request.getParameter("alncodg");
			
			BeanMatricula beanMatricula = ModelMatricula.getInstance().getMatriculaDoAluno(Integer.parseInt(alncodg));
			
			formMatriculaModulo.setMmcnmal(beanMatricula.getMtcnmal());
			formMatriculaModulo.setMmncgal(beanMatricula.getMtncgal());
			formMatriculaModulo.setMmncgmt(beanMatricula.getMtncodg());
			
			request.setAttribute("ls_modulo", ModelModulo.getInstance().getModulosParaMatricula(Integer.parseInt(alncodg)));
			request.setAttribute("ls_semestre", ModelSemestre.getInstance().getSemestresParaMatricula(Integer.parseInt(alncodg)));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		fw.setPath("/matriculaModuloNovo.do");
						
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
	
		
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormMatriculaModulo formMatriculaModulo = (FormMatriculaModulo)form;
		

			
		try {
			BeanMatriculaModulo beanMatriculaModulo = new BeanMatriculaModulo();
			BeanUtils.copyProperties(beanMatriculaModulo, formMatriculaModulo);
			
			ModelMatriculaModulo.getInstance().inserir(beanMatriculaModulo);
			fw.setPath("/alunoPesquisaMatriculaModulo.do");
			
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Aluno Matricula com sucesso!"));
			saveErrors(request, erros);
			
			//limpa o form de cadastro
			request.getSession().removeAttribute("formMatriculaModulo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}	
	
	
}
