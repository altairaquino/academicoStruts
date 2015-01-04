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
				&nbsp;Semestres > <bean:write name="semestre" property="smcdesc"/> > <bean:write name="cursosemestre" property="cmcdccs"/> (<bean:write name="cursosemestre" property="cmcdcue"/>) &nbsp; 
			</legend>
			<table width="100%">
			<tbody>
				<tr>
					<th> 
						Opções do Curso do Semestre
					</th>
					<th> 
						&nbsp;
					</th>
				</tr>
				<tr>
					<td>
						Listagem de Turmas
					</td>
					<td>
						<input type="button" value="Detalhes" onclick="window.location = 'actionTurma.do?m=lista&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Configuração de Parcelas
					</td>
					<td>
						<input type="button" value="Detalhes" onclick="window.location = 'actionParcelaCursoSemestre.do?m=lista&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Alunos com matrícula consolidada
					</td>
					<td>
						<input type="button" value="Detalhes" onclick="window.location = 'actionCursoSemestre.do?m=listaAlunos&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Impressão de Carnês de Mensalidade
					</td>
					<td>
						<input type="button" value="Detalhes" onclick="window.location = 'actionBoleto.do?m=imprimirCarneCursoSemestre&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" name="back" value="Voltar" onclick="window.location = 'actionCursoSemestre.do?m=lista&smncodg=<bean:write name="semestre" property="smncodg"/>';">&nbsp;
						<input type="button" value="Editar Curso" onclick="window.location = 'actionCursoSemestre.do?m=editar&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>';">
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