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
				&nbsp;Semestres > <bean:write name="semestre" property="smcdesc"/> &nbsp; 
			</legend>
			<table width="100%">
			<tbody>
				<tr>
					<th> 
						Opções do Semestre
					</th>
					<th> 
						&nbsp;
					</th>
				</tr>
				<tr>
					<td>
						Cursos
					</td>
					<td>
						<input type="button" value="Detalhes" onclick="window.location = 'actionCursoSemestre.do?m=lista&smncodg=<bean:write name="semestre" property="smncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Relatório de Resumo Financeiro Semestral (<bean:write name="semestre" property="smcdesc"/>).
					</td>
					<td>
						<input type="button" value="Visualizar" onclick="window.location='actionSemestre.do?m=relatorioFinanceiro&smncodg=<bean:write name="semestre" property="smncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Relatório Financeiro (Lista de Inadimplência em <bean:write name="semestre" property="smcdesc"/>).
					</td>
					<td>
						<input type="button" value="Visualizar" onclick="window.location='actionSemestre.do?m=relatorioFinanceiroPend&smncodg=<bean:write name="semestre" property="smncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Relatório de Alunos Cadastrados (<bean:write name="semestre" property="smcdesc"/>)
					</td>
					<td>
						<input type="button" value="Visualizar" onclick="window.location='actionSemestre.do?m=relatorioAlunos&smncodg=<bean:write name="semestre" property="smncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" name="back" value="Voltar" onclick="window.location = 'actionSemestre.do?m=lista';">
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