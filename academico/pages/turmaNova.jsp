<%@include file="topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>


</script>
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				<bean:write name="semestre" property="smcdesc"/> > <bean:write name="cursosemestre" property="cmcdccs"/> (<bean:write name="cursosemestre" property="cmcdcue"/>) > Nova Turma do Curso
			</legend>
			<html:errors/>
			<html:form action="/actionTurma" focus="tmncgdc">
			<html:hidden property="m" value="cadastro"/>
			<input type="hidden" name="tmncgcm" value="<bean:write name="cursosemestre" property="cmncodg"/>">
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Nova Turma do Curso do Semestre
					</th>
				</tr>
				<tr>
					<td>
						Disciplina
					</td>
					<td>
						<html:select property="tmncgdc" style="width: 350px;">
							<html:optionsCollection name="ls_disciplina" label="dccdesc" value="dcncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Professor
					</td>
					<td>
						<html:select property="tmncgpf" style="width: 350px;">
							<html:optionsCollection name="ls_professor" label="pfcnome" value="pfncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Período
					</td>
					<td>
						<html:text property="tmnperi" maxlength="1" size="2" onkeyup="criaMascara(this, '##');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Turma Extra?
					</td>
					<td>
						Sim <html:radio property="tmlextr" value="T"/> &nbsp;&nbsp;&nbsp;
						Não <html:radio property="tmlextr" value="F"/>						
					</td>
				</tr>
				<tr>
					<td>
						Capacidade
					</td>
					<td>
						<html:text property="tmnmax" maxlength="2" size="2" onkeyup="criaMascara(this, '##');"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit>Salvar Turma</html:submit>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" name="back" value="Cancelar" onclick="window.location = 'actionTurma.do?m=lista&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>';">
					</td>
				</tr>					
			</tbody>			
			</table>	
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>