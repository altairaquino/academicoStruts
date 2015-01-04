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

import br.com.falconsistemas.academico.struts.bean.BeanAluno;
import br.com.falconsistemas.academico.struts.form.FormAluno;
import br.com.falconsistemas.academico.struts.model.Banco;
import br.com.falconsistemas.academico.struts.model.ModelAluno;
import br.com.falconsistemas.academico.struts.model.ModelCidade;
import br.com.falconsistemas.academico.struts.model.ModelEstadoCivil;
import br.com.falconsistemas.academico.struts.model.ModelTipoLogradouro;
import br.com.falconsistemas.academico.utils.CPF;
import br.com.falconsistemas.academico.utils.ImpressaoBoleto;
import br.com.falconsistemas.academico.utils.ValidaObjeto;

public class ActionAluno extends DispatchAction {

	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		request.setAttribute("ls_estados", ModelCidade.getInstance().getEstados());
		request.setAttribute("ls_estadoscivis", ModelEstadoCivil.getInstance().getEstadosCivis());
//		request.setAttribute("ls_religiao", ModelReligiao.getInstance().getReligioes());
		request.setAttribute("ls_logradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
		request.setAttribute("ls_cidades", ModelCidade.getInstance().getCidades());
		
		fw.setPath("/alunoCadastro.do");		
		
		return fw;
	}
	
	public ActionForward mostraDados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		try {
			String alncodg = request.getParameter("alncodg");
			
			if (alncodg != null){
				BeanAluno aluno = ModelAluno.getInstance().getAluno(Integer.parseInt(alncodg));
				request.setAttribute("aluno", aluno);
				fw.setPath("/alunoDados.do");
			}else{
				fw.setPath("/alunoPesquisaDados.do");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return fw;
	}
	
	
	
	public ActionForward pesquisaBoleto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAluno formAluno = (FormAluno) form;
		
		if (!ValidaObjeto.validaString(formAluno.getAlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
		}else{
			if (formAluno.getAlcnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
			}
		}
		
		if (erros.isEmpty()){
			List<BeanAluno> listaAluno = ModelAluno.getInstance().getAlunosPorNomeCpf(formAluno.getAlcnome());
			if (listaAluno.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno encontrado!"));
			}else{
				request.setAttribute("ls_alunos", listaAluno);
			}
		}
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoPesquisaBoleto.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaDocumento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAluno formAluno = (FormAluno) form;
		
		if (!ValidaObjeto.validaString(formAluno.getAlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
		}else{
			if (formAluno.getAlcnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
			}
		}
		
		if (erros.isEmpty()){
			List<BeanAluno> listaAluno = ModelAluno.getInstance().getAlunosPorNomeCpf (formAluno.getAlcnome());
			if (listaAluno.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno encontrado!"));
			}else{
				request.setAttribute("ls_alunos", listaAluno);
			}
		}
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoPesquisaDoc.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward documentos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		String alncodg = request.getParameter("alncodg");
		
		request.setAttribute("aluno", ModelAluno.getInstance().getAluno(Integer.parseInt(alncodg)));
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoDoc.do");
				
		return fw;
	}
	
	public ActionForward declaracao(ActionMapping mapping, ActionForm form,
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
		        map.put("JASPER",path + "jasper/declaracao_curso.jasper");
	            map.put("IMG_FUNDO", path + "imagens/fundo.jpg");
	            map.put("IMG_LOGO", path + "imagens/logo_iesl_peq.jpg");
	            map.put("ALNCODG", new Integer(aluno));
				String caminho = ImpressaoBoleto.gerarBoleto(map);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition","attachment; filename=\"declaracao.pdf\"");
			    fw.setPath("/boletos/"+caminho);				
		
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward pesquisaDados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAluno formAluno = (FormAluno) form;
		
		if (!ValidaObjeto.validaString(formAluno.getAlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
		}else{
			if (formAluno.getAlcnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
			}
		}
		
		if (erros.isEmpty()){
			List<BeanAluno> listaAluno = ModelAluno.getInstance().getAlunosPorNomeCpf(formAluno.getAlcnome());
			if (listaAluno.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno encontrado!"));
			}else{
				request.setAttribute("ls_alunos", listaAluno);
			}
		}
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoPesquisaDados.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaNota(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAluno formAluno = (FormAluno) form;
		
		if (!ValidaObjeto.validaString(formAluno.getAlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
		}else{
			if (formAluno.getAlcnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
			}
		}
		
		if (erros.isEmpty()){
			List<BeanAluno> listaAluno = ModelAluno.getInstance().getAlunosPorNomeCpf(formAluno.getAlcnome());
			if (listaAluno.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno encontrado!"));
			}else{
				request.setAttribute("ls_alunos", listaAluno);
			}
		}
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoPesquisaNota.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaDisciplina(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAluno formAluno = (FormAluno) form;
		
		if (!ValidaObjeto.validaString(formAluno.getAlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
		}else{
			if (formAluno.getAlcnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do aluno."));
			}
		}
		
		if (erros.isEmpty()){
			List<BeanAluno> listaAluno = ModelAluno.getInstance().getAlunosPorNomeCpf(formAluno.getAlcnome());
			if (listaAluno.isEmpty()){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum aluno encontrado!"));
			}else{
				request.setAttribute("ls_alunos", listaAluno);
			}
		}
		
		request.getSession().removeAttribute("formAluno");
		
		fw.setPath("/alunoPesquisaInsereDisciplina.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
		
	public ActionForward salvarAluno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAluno formAluno = (FormAluno)form;
		BeanAluno beanAluno = new BeanAluno();
		
		BeanUtils.copyProperties(beanAluno, formAluno);
		
		if(!ValidaObjeto.validaString(formAluno.getAlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome do aluno é requerido."));
		}
		
		if(!ValidaObjeto.validaString(formAluno.getAlccpf())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","C.P.F. do aluno é obrigatório."));
		}else{
			if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formAluno.getAlccpf()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","O C.P.F. digitado é incorreto."));
			}else{
				if (ModelAluno.getInstance().cpfExiste(ValidaObjeto.removeCharOfInteger(formAluno.getAlccpf()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","C.P.F. já cadastrado no sistema."));
				}
			}
		}
		
		if(!ValidaObjeto.validaData(formAluno.getAldnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Nascimento é obrigatória."));
		}
		if(formAluno.getAldnasc().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Naturalidade é obrigatória."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlcmae())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Mãe do aluno é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlcrg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","R.G. do aluno é obrigatório."));
		}		
		if(!ValidaObjeto.validaString(formAluno.getAlcoerg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Orgão expedidor do R.G. do aluno é obrigatório."));
		}		
		if(!ValidaObjeto.validaString(formAluno.getAlctiel())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Título Eleitoral é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlczote())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Zona do Título Eleitoral é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlciesg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Instituição de Ensino Médio é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlcsete())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Seção do Título Eleitoral é obrigatório."));
		}
		if(!ValidaObjeto.validaData(formAluno.getAlddete())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Título Eleitoral é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlclogr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Endereço é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlccomp())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Complemento do endereço é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","CEP é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlcbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Bairro é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw = this.cadastro(mapping, form, request, response);
		}else{		
			try {				
				ModelAluno.getInstance().inserir(beanAluno);
				request.getSession().removeAttribute("formAluno");
				request.setAttribute("aluno", ModelAluno.getInstance().getAlunosPorNomeCpf(ValidaObjeto.removeCharOfInteger(formAluno.getAlccpf())).get(0));
				fw.setPath("/alunoCadastroSucesso.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
				
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormAluno formAluno =(FormAluno)form;
		
		String alncodg = request.getParameter("alncodg");
		
		BeanAluno beanAluno = ModelAluno.getInstance().getAluno(Integer.parseInt(alncodg));
		BeanUtils.copyProperties(formAluno, beanAluno);
		
		
		request.setAttribute("ls_estados", ModelCidade.getInstance().getEstados());
		request.setAttribute("ls_estadoscivis", ModelEstadoCivil.getInstance().getEstadosCivis());
//		request.setAttribute("ls_religiao", ModelReligiao.getInstance().getReligioes());
		request.setAttribute("ls_logradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
		request.setAttribute("ls_cidades", ModelCidade.getInstance().getCidades());
		
		fw.setPath("/alunoEditar.do");		
				
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAluno formAluno = (FormAluno)form;
		BeanAluno beanAluno = new BeanAluno();
		
		BeanUtils.copyProperties(beanAluno, formAluno);
		
		if(!ValidaObjeto.validaString(formAluno.getAlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome do aluno é requerido."));
		}
		
		if(!ValidaObjeto.validaString(formAluno.getAlccpf())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","C.P.F. do aluno é obrigatório."));
		}else{
			if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formAluno.getAlccpf()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","O C.P.F. digitado é incorreto."));
			}
		}
		
		if(!ValidaObjeto.validaData(formAluno.getAldnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data de Nascimento é obrigatória."));
		}
		if(formAluno.getAldnasc().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Naturalidade é obrigatória."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlcmae())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Mãe do aluno é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlcrg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","R.G. do aluno é obrigatório."));
		}		
		if(!ValidaObjeto.validaString(formAluno.getAlcoerg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Orgão expedidor do R.G. do aluno é obrigatório."));
		}		
		if(!ValidaObjeto.validaString(formAluno.getAlctiel())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Título Eleitoral é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlczote())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Zona do Título Eleitoral é obrigatório."));
		}		
		if(!ValidaObjeto.validaString(formAluno.getAlcsete())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Seção do Título Eleitoral é obrigatório."));
		}
		if(!ValidaObjeto.validaData(formAluno.getAlddete())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Título Eleitoral é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlclogr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Endereço é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlccomp())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Complemento do endereço é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","CEP é obrigatório."));
		}
		if(!ValidaObjeto.validaString(formAluno.getAlcbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Bairro é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw = this.editar(mapping, form, request, response);
		}else{		
			try {
				fw.setPath("/actionAluno.do?m=mostraDados&alncodg="+beanAluno.getAlncodg());
				ModelAluno.getInstance().update(beanAluno);
				request.getSession().removeAttribute("formAluno");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
				
		return fw;
	}
	
	

}
