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
				&nbsp;  Disciplinas do Aluno no Semestre     &nbsp;
			</legend>
			<html:form action="actionMatriculaTurma">
			<html:hidden property="m" value="updateNotasAluno"/>
			<input type="hidden" name="ttncgal" value="<bean:write name="matricula" property="mtncgal"/>">
			<table>
			<tbody>
				<tr>
					<th colspan="5">
						&nbsp;
					</th>
				</tr>
				<tr>
					<td colspan="5">
						<strong> Aluno:</strong> <bean:write name="matricula" property="mtcnmal"/><br>
						<strong> Curso:</strong> <bean:write name="matricula" property="mtcdccs"/><br>
						<strong> Unidade de Ensino:</strong> <bean:write name="matricula" property="mtcdcue"/><br>
						<strong> Semestre de Ingresso:</strong> <bean:write name="matricula" property="mtcdcsm"/><br>
						<strong> Situação da Matrícula:</strong> <bean:write name="matricula" property="mtcdcsr"/>						
					</td>
				</tr>
				<logic:notEmpty name="ls_matriculaturma">
				<tr>
					<th colspan="5" style="text-align: center; background-color: #DDD;">
						Histórico de Disciplinas Matriculadas
					</th>
				</tr>
				<tr>
					<th colspan="5">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th>
						Semestre
					</th>
					<th>
						Disciplina
					</th>
					<th>
						Professor(a)
					</th>
					<th>
						Nota
					</th>
					<th>
						Situação
					</th>					
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_matriculaturma">
				<html:hidden name="b" property="ttncodg"/>
				<html:hidden name="b" property="ttncgtm"/>
				<html:hidden name="b" property="ttncgmt"/>
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="ttcdcsm"/>
					</td>
					<td>
						<bean:write name="b" property="ttcdcdc"/>
					</td>
					<td>
						<bean:write name="b" property="ttcnmpf"/>
					</td>
					<th>
						<logic:equal name="b" property="ttnnota" value="0,00">
							<html:text property="ttnnota" value="" size="5" maxlength="5" style="text-align: right;" onkeydown="Formata(this,4,event,2)"/>
						</logic:equal>
						<logic:notEqual name="b" property="ttnnota" value="0,00">
							<html:text property="ttnnota" name="b" size="5" maxlength="5" style="text-align: right;" onkeydown="Formata(this,4,event,2)"/>
						</logic:notEqual>						
					</th>
					<th>
						<bean:write name="b" property="ttcdcst"/>
					</th>
				</tr>				
				</logic:iterate>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5" style="text-align: right;"> 
						<html:submit>Salvar Notas</html:submit>
					</td>
				</tr>
				</logic:notEmpty>
				<tr>
					<td colspan="5">
						<input type="button" name="back" value="Voltar" onclick="window.location = 'actionMatriculaTurma.do?m=listaSemestreAtual&alncodg=<bean:write name="matricula" property="mtncgal"/>'">
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