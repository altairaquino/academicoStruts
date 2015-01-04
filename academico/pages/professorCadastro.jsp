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
				&nbsp;   Cadastro de Professores    &nbsp;
			</legend>
			<table style="width: 500px;">
			<tbody>
				<html:form action="actionProfessor" focus="pfcnome">
				<html:hidden property="m" value="cadastro"/>
				<tr style="padding: 6px;">
					<th colspan="2" style="text-align: center;"> Novo Professor </th>
				</tr>
				<tr style="padding: 6px;">
					<td> 
						Nome 
					</td>
					<td>
						<html:text property="pfcnome" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr style="padding: 6px;">
					<td style="text-align: right;" colspan="2">
						<html:submit>Salvar</html:submit>
					</td>
				</tr>				
				</html:form>
				<tr style="padding: 6px;">
					<th colspan="2"> 
						&nbsp;
					</th>
				</tr>
				<tr style="padding: 6px;">
					<th colspan="2" style="text-align: center;"> 
						Lista de Professores Cadastrados
					</th>					
				</tr>
				<tr style="padding: 6px;">
					<th colspan="2"> 
						Nome
					</th>
				</tr>
				<logic:iterate id="b" name="ls_professor">
				<tr style="padding: 6px;">
					<td colspan="2"> 
						<bean:write name="b" property="pfcnome"/>
					</td>					
				</tr>
				</logic:iterate>
				<tr style="padding: 6px;">
					<th colspan="2"> 
						&nbsp;
					</th>
				</tr>
				<tr style="padding: 6px;">
					<td colspan="2">
						<input type="button" value="Voltar" onclick="window.location = 'home.do'">
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