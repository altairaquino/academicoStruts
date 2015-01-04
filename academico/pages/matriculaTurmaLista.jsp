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
				&nbsp;  Confirmação de Inclusão de disciplinas do aluno    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="3">
						&nbsp;
					</th>
				</tr>
				<tr>
					<td colspan="3">
						<strong> Aluno:</strong> <bean:write name="matricula" property="mtcnmal"/><br>
						<strong> Curso:</strong> <bean:write name="matricula" property="mtcdccs"/><br>
						<strong> Semestre de Ingresso:</strong> <bean:write name="matricula" property="mtcdcsm"/><br>
						<strong> Situação da Matrícula:</strong> <bean:write name="matricula" property="mtcdcsr"/>						
					</td>
				</tr>
				<tr>
					<th colspan="3">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th colspan="3" style="text-align: center; background-color: #DDD;">
						Disciplinas Matriculadas
					</th>
				</tr>
				<tr>
					<th colspan="3">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th>
						Disciplina
					</th>
					<th>
						Professor(a)
					</th>					
					<th>
						Situação Atual
					</th>					
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_matriculaturma">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="ttcdcdc"/>
					</td>
					<td>
						<bean:write name="b" property="ttcnmpf"/>
					</td>					
					<td>
						<bean:write name="b" property="ttcdcst"/>
					</td>					
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="3">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th colspan="3">
						<input name="back" type="button" value="Escolher outro Aluno" onclick="window.location = 'alunoPesquisaInsereDisciplina.do'">
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