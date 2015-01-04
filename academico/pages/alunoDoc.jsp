	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Solicitação de documentos do Aluno    &nbsp;
			</legend>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Aluno: <bean:write name="aluno" property="alcnome"/><br> CPF: <bean:write name="aluno" property="alccpf"/> 
					</th>
				</tr>
				<tr>
					<th colspan="2">
						Listagem de Documentos Disponíveis 
					</th>
				</tr>
				<tr>
					<td>
						 Declaração de aluno em curso
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionAluno.do?m=declaracao&aluno=<bean:write name="aluno" property="alncodg"/>'">
					</td>
				</tr>				
				<tr>
					<td>
						 Histórico Escolar
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionTurma.do?m=historico&aluno=<bean:write name="aluno" property="alncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="button" name="back" value="Voltar" onclick="window.location = 'alunoPesquisaDoc.do'"> 
					</th>
				</tr>
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
<%@page import="br.com.falconsistemas.academico.struts.bean.BeanBoleto"%>
</html>