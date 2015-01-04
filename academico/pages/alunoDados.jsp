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
				&nbsp;   Dados Cadastrais do Aluno    &nbsp;
			</legend>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td colspan="3" style="text-align: right;">
						<html:form method="post" action="/actionAluno">
							<html:hidden property="m" value="editar"/>
							<html:hidden name="aluno" property="alncodg"/>
							<html:submit>Editar Dados</html:submit>
						</html:form>
					</td>
				</tr>
				<tr>
					<td>
						<b>Nome</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcnome" />
					</td>
				</tr>
				<tr>
					<td>
						<b>C.P.F.</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alccpf"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Sexo</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcsexo"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Data de Nascimento</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="aldnasc"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Naturalidade</b>
					</td>
					<td>
						<bean:write name="aluno" property="alccndc"/> - <bean:write name="aluno" property="alccnuf"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Nome do Pai</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcpai"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Nome do Mãe</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcmae"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Estado Civil</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcdcec"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>R.G.</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcrg"/>&nbsp;
						<b>Org. Exp.:</b>&nbsp;<bean:write name="aluno" property="alcoerg"/>&nbsp;
						<b>U.F.:</b>&nbsp;<bean:write name="aluno" property="alcufrg"/> -
						<b>Data Exp.:</b> <bean:write name="aluno" property="alddete"/>							
					</td>
				</tr>
				<tr>
					<td>
						<b>Título Eleitoral</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alctiel"/>&nbsp;
						<b>Zona:</b> &nbsp;<bean:write name="aluno" property="alczote"/>&nbsp;
						<b>Seção:</b> &nbsp;<bean:write name="aluno" property="alcsete"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Inst. Ens. Médio</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alciesg"/>
					</td>
				</tr>				
				<tr>
					<td>
						<b>Nacionalidade</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcnaci"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Contatos</b>
					</td>
					<td colspan="2">
						<b>Fone Fixo:</b> <bean:write name="aluno" property="alcfone"/>
						<b>Celular:</b> <bean:write name="aluno" property="alccell"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Endereço</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alcdctl"/>&nbsp;<bean:write name="aluno" property="alclogr"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Complemento</b>
					</td>
					<td colspan="2">
						<bean:write name="aluno" property="alccomp"/>
					</td>
				</tr>
				<tr>
					<td>
						<b>Estado/Cidade</b>
					</td>
					<td>						
						<bean:write name="aluno" property="alccedc"/> - <bean:write name="aluno" property="alcceuf"/>	
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<b>C.E.P.:</b>&nbsp;<bean:write name="aluno" property="alccep"/>&nbsp;&nbsp;
						<b>Bairro:</b>&nbsp;<bean:write name="aluno" property="alcbair"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>				
				<tr>
					<td colspan="3">
						<input type="button" value="Voltar" onclick="window.location = 'alunoPesquisaDados.do'" class="btn_hot">						
					</td>
				</tr>				
			
			</tbody>			
			</table>
			
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>