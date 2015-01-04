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
				<tr>
					<th colspan="5" style="text-align: right;">
						<input type="button" value="Alterar Notas" onclick="window.location = 'actionMatriculaTurma.do?m=matriculasDoAluno&alncodg=<bean:write name="matricula" property="mtncgal"/>'">
					</th>
				</tr>
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
					<td>
						<bean:write name="b" property="ttnnota"/>
					</td>				
					<td>
						<bean:write name="b" property="ttcdcst"/>
					</td>					
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="5">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th colspan="5">
						<input name="back" type="button" value="Nova Pesquisa" onclick="window.location = 'alunoPesquisaNota.do'">
					</th>
				</tr>			
			</tbody>	
			</table>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>