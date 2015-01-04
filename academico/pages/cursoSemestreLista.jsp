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
				<legend><bean:write name="semestre" property="smcdesc"/> > Cursos do Semestre</legend>
				<table>
					<tbody>
					<tr>
						<th>
							Curso
						</th>
						<th>
							Unidade de Ensino
						</th>
						<th>
							Turno
						</th>
						<th>
							Valor Mensal
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_cursosemestre">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="cmcdccs"/>
						</td>
						<td>
							<bean:write name="b" property="cmcdcue"/>
						</td>
						<td>
							<bean:write name="b" property="cmcdctn"/>
						</td>
						<td>
							R$ <bean:write name="b" property="cmyvalr"/>
						</td>
						<td>
							<input type="button" value="Detalhes" onclick="window.location = 'actionCursoSemestre.do?m=opcoesCursoSemestre&cmncodg=<bean:write name="b" property="cmncodg"/>'">
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="5">
							<input type="button" value="Voltar" name="back" onclick="window.location = 'actionSemestre.do?m=opcoesSemestre&smncodg=<bean:write name="semestre" property="smncodg"/>'">
							<logic:present name="semestreaberto">
								<input type="button" value="Novo Curso" onclick="window.location = 'actionCursoSemestre.do?m=novoCurso'">
							</logic:present>
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