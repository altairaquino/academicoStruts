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
				&nbsp;   Matricula Efetuado Com Sucesso    &nbsp;
			</legend>
			<table width="400">
			<tbody>
				<tr>
					<td colspan="2"> &nbsp;</td>
				</tr> 
				<tr>
					<th style="width: 100px;">
						Aluno
					</th>
					<th style="width: 300px;">
						<bean:write name="aluno_mat" property="alcnome"/>
					</th>
				</tr>
				<tr>
					<th>
						Curso
					</th>
					<th>
						<bean:write name="curso_mat" property="cmcdccs"/>
					</th>
				</tr>
				<tr>
					<th>
						Unidade de Ensino
					</th>
					<th>
						<bean:write name="curso_mat" property="cmcdcue"/>
					</th>
				</tr>
				<tr>
					<th>
						Turno
					</th>
					<th>
						<bean:write name="curso_mat" property="cmcdctn"/>
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="Matricular Outro Aluno" onclick="window.location = 'alunoPesquisaMatricula.do'"> 	
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="Adcionar Disciplinas do Aluno" onclick="window.location = 'actionMatricula.do?m=montaMatricula&alncodg=<bean:write name="aluno_mat" property="alncodg"/>'"> 	
					</td>
				</tr>
				<tr>
					<td colspan="2"> &nbsp;</td>
				</tr>			
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>