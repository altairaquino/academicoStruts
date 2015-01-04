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
					<bean:write name="semestre" property="smcdesc"/> > <bean:write name="cursosemestre" property="cmcdccs"/> (<bean:write name="cursosemestre" property="cmcdcue"/>) > Turmas do Curso
				</legend>
				<table>
					<tbody>
					<tr>
						<th>
							Peri
						</th>
						<th>
							Disciplina
						</th>
						<th>
							Professor(a)
						</th>
						<th>
							Capac
						</th>
						<th>
							Matr.
						</th>
						<th>
							Extra?
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_turma">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="tmnperi"/>º
						</td>
						<td>
							<bean:write name="b" property="tmcdcdc"/>
						</td>
						<td>
							<bean:write name="b" property="tmcnmpf"/>
						</td>
						<td>
							<bean:write name="b" property="tmnmax"/>
						</td>
						<td>
							<bean:write name="b" property="tmnmatr"/>
						</td>
						<td>
							<logic:equal name="b" property="tmlextr" value="T">
								SIM								
							</logic:equal>
							<logic:equal name="b" property="tmlextr" value="F">
								NAO								
							</logic:equal>
						</td>
						<td>
							<input type="image" src="imagens/usuario.gif" title="Visualizar Alunos" onclick="window.location = 'actionMatriculaTurma.do?m=matriculasDaTurma&tmncodg=<bean:write name="b" property="tmncodg"/>'">&nbsp;
							<input type="image" src="imagens/detalhe.gif" title="Diário de Frequencia de Sala" onclick="window.location = 'actionTurma.do?m=relatorio&tmncodg=<bean:write name="b" property="tmncodg"/>'">&nbsp;
							<input type="image" src="imagens/mail.gif" title="Recibo de Pagamento" onclick="window.location = 'actionTurma.do?m=recibo&tmncodg=<bean:write name="b" property="tmncodg"/>'">&nbsp;
							<logic:present name="semestreaberto">
								<input type="image" src="imagens/editar.gif" title="Editar Turma" onclick="window.location = 'actionTurma.do?m=editar&tmncodg=<bean:write name="b" property="tmncodg"/>'">
							</logic:present>
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="5">
							<input type="button" value="Voltar" name="back" onclick="window.location = 'actionCursoSemestre.do?m=opcoesCursoSemestre&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">
							<logic:present name="semestreaberto">
								<input type="button" value="Inserir Nova Turma" onclick="window.location = 'actionTurma.do?m=novaTurma'">
							</logic:present>
							<logic:empty name="ls_turma">
								<input type="button" value="Importa Turmas" onclick="window.location = 'actionTurma.do?m=importar&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">							
							</logic:empty>
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