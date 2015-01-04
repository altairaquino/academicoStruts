<%@include file="topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<fieldset>
			<legend>
				&nbsp;Semestres > <bean:write name="semestre" property="smcdesc"/> > <bean:write name="cursosemestre" property="cmcdccs"/> &nbsp; 
			</legend>
			<html:form action="/actionTurma" focus="tmncgpf">
			<html:hidden property="m" value="update"/>
			<html:hidden property="tmncodg"/>
			<html:hidden property="tmncgcm"/>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Atualizar Turma do Curso do Semestre
					</th>
				</tr>
				<tr>
					<td>
						Disciplina
					</td>
					<td>
						<html:select property="tmncgdc" style="width: 350px;" disabled="true">
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
						<html:text property="tmnperi" maxlength="1" size="2" onkeyup="criaMascara(this, '##');" disabled="true"></html:text>
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
						Qtd. máxima
					</td>
					<td>
						<html:text property="tmnmax" maxlength="2" size="2" onkeyup="criaMascara(this, '##');"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit>Atualizar Turma</html:submit>
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