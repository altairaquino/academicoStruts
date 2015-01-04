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
				&nbsp;   Aluno Cadastrado com sucesso    &nbsp;
			</legend>
			<table>
			<tbody>
				<tr>
					<th colspan="2">&nbsp; </th>
				</tr>
				<tr>
					<td>
						Aluno 
					</td>
					<td>
						<bean:write name="aluno" property="alcnome"/>
					</td>
				</tr>
				<tr>
					<td>
						CPF 
					</td>
					<td>
						<bean:write name="aluno" property="alccpf"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">Opções </th>
				</tr>
				<tr>
					<td>
						Cadastrar outro Aluno
					</td>
					<td>
						<input type="button" value="Selecionar" onclick="window.location = 'actionAluno.do?m=cadastro'">
					</td>
				</tr>
				<tr>
					<td>
						Matricular o Aluno
					</td>
					<td>
						<input type="button" value="Selecionar" onclick="window.location = 'actionMatricula.do?m=nova&alccpf=<bean:write name="aluno" property="alccpf"/>'">
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