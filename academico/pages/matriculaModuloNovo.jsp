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
				&nbsp;   Matricula Aluno no módulo    &nbsp;
			</legend>
			<html:form action="actionMatriculaModulo">
			<html:hidden property="m" value="cadastro"/>
			<html:hidden property="mmncgmt"/>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th> Matricula por Módulo </th>
				</tr>
				<tr>
					<th>
						Aluno
					</th>
					<th>
						<bean:write name="formMatriculaModulo" property="mmncgal"/> - <bean:write name="formMatriculaModulo" property="mmcnmal"/>
					</th>
				</tr>
				<tr>
					<th>
						Módulo
					</th>
					<td>
						<html:select property="mmncgmo" style="width: 250px;">
							<html:optionsCollection name="ls_modulo" value="moncodg" label="mocdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						Semestre
					</th>
					<td>
						<html:select property="mmncgsm" style="width: 250px;">
							<html:optionsCollection name="ls_semestre" value="smncodg" label="smcdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" onclick="window.location = 'alunoPesquisaMatriculaModulo.do'">
					</td>
					<td style="text-align: right;">
						<input type="button" value="Matricular" class="btn_hot" onclick="if (window.confirm('Confirmar a matricula do aluno?')){this.form.submit();}">
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
