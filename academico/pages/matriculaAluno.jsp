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
				&nbsp;   Matricula de Aluno    &nbsp;
			</legend>
			<html:form action="actionMatricula" focus="mtncgcm" onsubmit="return window.confirm('Confirmar a matricula do aluno com os dados abaixo?');">
			<html:hidden property="m" value="cadastro"/>
			<html:hidden property="mtncgal"/>
			<table>
			<tbody>
				<tr>
					<th colspan="2">
						Efetuar Matricula 
					</th>
				</tr>
				<tr>
					<td>
						Aluno
					</td>
					<td>
						<html:text property="mtcnmal" size="50" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						CPF
					</td>
					<td>
						<html:text property="mtccpf" size="14" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Curso
					</td>
					<td>
						<html:select property="mtncgcm" style="width: 313px;">
							<html:optionsCollection name="ls_cursos" label="cmcdccs" value="cmncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Tipo Ingresso
					</td>
					<td>
						<html:select property="mtncgti" style="width: 313px;">
							<html:optionsCollection name="ls_tipoingresso" label="ticdesc" value="tincodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Percentual Desconto(%)
					</td>
					<td>
						<html:text property="mtnpcdc" size="8"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="Voltar" class="btn_hot" onclick="window.location = 'alunoPesquisaMatricula.do'">
						<html:submit>Matricular Aluno</html:submit>
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