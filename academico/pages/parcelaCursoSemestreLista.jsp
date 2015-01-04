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
					<bean:write name="semestre" property="smcdesc"/> > <bean:write name="cursosemestre" property="cmcdccs"/> (<bean:write name="cursosemestre" property="cmcdcue"/>) > Parcelas do Curso
				</legend>
				<table>
					<tbody>
					<tr>
						<th>
							Vencimento
						</th>
						<th>
							Data da Multa
						</th>
						<th>
							Data dos Juros
						</th>
						<th>
							Data da Emissão
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_parcelacursosemestre">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="pcdvenc"/>
						</td>
						<td>
							<bean:write name="b" property="pcdmult"/>
						</td>
						<td>
							<bean:write name="b" property="pcdjurs"/>
						</td>
						<td>
							<bean:write name="b" property="pcdemis"/>
						</td>
						<td>
							<logic:empty name="b" property="pcdemis">
								<input type="button" value="Emitir Parcela" onclick="if (window.confirm('Emitir a parcela?')) {window.location = 'actionParcelaCursoSemestre.do?m=emitir&pcncodg=<bean:write name="b" property="pcncodg"/>&pcncgcm=<bean:write name="cursosemestre" property="cmncodg"/>'}">								
								<input type="button" value="Editar" onclick="window.location = 'actionParcelaCursoSemestre.do?m=editar&pcncodg=<bean:write name="b" property="pcncodg"/>'">
							</logic:empty>
							<logic:notEmpty name="b" property="pcdemis">
								Parcela Emitida								
							</logic:notEmpty>
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="5">
							<input type="button" value="Voltar" name="back" onclick="window.location = 'actionCursoSemestre.do?m=opcoesCursoSemestre&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">
							<logic:present name="semestreaberto">
								<input type="button" value="Inserir Nova Parcela" onclick="window.location = 'actionParcelaCursoSemestre.do?m=novaParcela'">
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