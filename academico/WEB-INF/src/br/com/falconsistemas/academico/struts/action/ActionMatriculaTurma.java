package br.com.falconsistemas.academico.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import br.com.falconsistemas.academico.struts.bean.BeanMatriculaTurma;
import br.com.falconsistemas.academico.struts.bean.BeanSemestre;
import br.com.falconsistemas.academico.struts.bean.BeanTurma;
import br.com.falconsistemas.academico.struts.model.ModelMatricula;
import br.com.falconsistemas.academico.struts.model.ModelMatriculaTurma;
import br.com.falconsistemas.academico.struts.model.ModelSemestre;
import br.com.falconsistemas.academico.struts.model.ModelTurma;
import br.com.falconsistemas.academico.utils.Utils;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionMatriculaTurma extends DispatchAction {

	
	public ActionForward matriculasDaTurma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String tmncodg = request.getParameter("tmncodg");
		fw.setPath("/turmaAlunos.do");
		
		BeanTurma beanTurma = ModelTurma.getInstance().getTurma(Integer.parseInt(tmncodg));
		request.getSession().setAttribute("turma", beanTurma);
		
		List<BeanMatriculaTurma> l = ModelMatriculaTurma.getInstance().getMatriculasDaTurma(Integer.parseInt(tmncodg));
		
		if (l.isEmpty()){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Não há alunos matriculados para esta turma!"));
			saveErrors(request, erros);
		}
		
		request.setAttribute("ls_matriculaturma", l);
		
		return fw;
	}
	
	public ActionForward matriculasDoAluno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		String alncodg = request.getParameter("alncodg");
		fw.setPath("/matriculaTurmaAlunoLista.do");
		
		List<BeanMatriculaTurma> l = ModelMatriculaTurma.getInstance().getMatriculaTurmaDoAluno(Integer.parseInt(alncodg));
		request.setAttribute("matricula", ModelMatricula.getInstance().getMatriculaDoAluno(Integer.parseInt(alncodg)));
		
		if (l.isEmpty()){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Não há matricula para este aluno!"));
			saveErrors(request, erros);
		}
		
		request.setAttribute("ls_matriculaturma", l);
		
		return fw;
	}
	
	public ActionForward listaSemestreAtual(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
				
		try {
			
			String alncodg = request.getParameter("alncodg");	
			//BeanSemestre sem = ModelSemestre.getInstance().semestreAtual();
			request.setAttribute("matricula", ModelMatricula.getInstance().getMatriculaDoAluno(Integer.parseInt(alncodg)));
			request.setAttribute("ls_matriculaturma", ModelMatriculaTurma.getInstance().getMatriculaTurmaDoAluno(Integer.parseInt(alncodg)/*, Integer.parseInt(sem.getSmncodg())*/));
			
			fw.setPath("/matriculaTurmaListaSemestre.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward updateNotas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		BeanMatriculaTurma beanMatriculaTurma = new BeanMatriculaTurma();
		String cmncodg = request.getParameter("ttncgcm");
		
		fw.setPath("/actionTurma.do?m=lista&cmncodg="+cmncodg);
		
		String [] ttncgtm = request.getParameterValues("ttncgtm");
		String [] ttncodg = request.getParameterValues("ttncodg");
		String [] ttnnota = request.getParameterValues("ttnnota");
		
		try {
			boolean alterou = false;
			for (int i = 0; i < ttncodg.length; i++) {
				if (ttnnota[i] != null){
					beanMatriculaTurma.setTtncgtm(ttncgtm[i]);
					beanMatriculaTurma.setTtncodg(ttncodg[i]);
					beanMatriculaTurma.setTtnnota(ttnnota[i]);
					if (!ttnnota[i].trim().equals("")){
						if (ValidaObjeto.validaFloat(Utils.converteFloatBR(ttnnota[i]))){
							if (Float.parseFloat(Utils.converteFloatBR(ttnnota[i])) >= 0 &&
									Float.parseFloat(Utils.converteFloatBR(ttnnota[i])) <= 10){
								ModelMatriculaTurma.getInstance().updateNota(beanMatriculaTurma);
								alterou = true;
							}
						}
					}else{						
						ModelMatriculaTurma.getInstance().updateNotaNull(beanMatriculaTurma);
						alterou = true;
					}
				}				
			}			
			
			if (alterou){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Notas atualizadas com sucesso!"));
				saveErrors(request, erros);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward updateNotasAluno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		BeanMatriculaTurma beanMatriculaTurma = new BeanMatriculaTurma();
		
		fw.setPath("/actionMatriculaTurma.do?m=listaSemestreAtual&alncodg="+request.getParameter("ttncgal"));
		
		String [] ttncgtm = request.getParameterValues("ttncgtm");
		String [] ttncodg = request.getParameterValues("ttncodg");
		String [] ttnnota = request.getParameterValues("ttnnota");
		
		try {
			boolean alterou = false;
			for (int i = 0; i < ttncodg.length; i++) {
				if (ttnnota[i] != null){
					beanMatriculaTurma.setTtncgtm(ttncgtm[i]);
					beanMatriculaTurma.setTtncodg(ttncodg[i]);
					beanMatriculaTurma.setTtnnota(ttnnota[i]);
					if (!ttnnota[i].trim().equals("")){
						if (ValidaObjeto.validaFloat(Utils.converteFloatBR(ttnnota[i]))){
							if (Float.parseFloat(Utils.converteFloatBR(ttnnota[i])) >= 0 &&
									Float.parseFloat(Utils.converteFloatBR(ttnnota[i])) <= 10){
								ModelMatriculaTurma.getInstance().updateNota(beanMatriculaTurma);
								alterou = true;
							}
						}
					}else{						
						ModelMatriculaTurma.getInstance().updateNotaNull(beanMatriculaTurma);
						alterou = true;
					}
				}				
			}			
			
			if (alterou){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Notas atualizadas com sucesso!"));
				saveErrors(request, erros);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	

	public ActionForward finaliza(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
						
		String alncodg = request.getParameter("alncodg");
				
		try {
			
			ModelMatricula.getInstance().finaliza(Integer.parseInt(alncodg));
			BeanSemestre semestre = ModelSemestre.getInstance().semestreAtual();
			
			List<BeanMatriculaTurma> l = ModelMatriculaTurma.getInstance().getMatriculaTurmaDoAlunoSemestre(
					Integer.parseInt(alncodg),
					Integer.parseInt(semestre.getSmncodg()));
			
			if (l.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Não há disciplinas escolhidas!"));
				saveErrors(request, erros);
				fw.setPath("/matriculaPage.do");
			}else{
				request.setAttribute("ls_matriculaturma",l);
				request.setAttribute("matricula", ModelMatricula.getInstance().getMatriculaDoAluno(Integer.parseInt(alncodg)));							
				fw.setPath("/matriculaTurmaLista.do");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
								
		return fw;
	}
	

}
