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
				&nbsp;   Pesquisa   &nbsp;
			</legend>
			<html:form method="action" action="actionAluno" focus="alcnome">
			<html:hidden property="m" value="pesquisaBoleto"/>
			<table>
			<tbody>
				<tr>
					<th colspan="4">Use, no mínimo, 3 caracteres para a pesquisa.</th>
				</tr>
				<tr>
					<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Nome, CPF &nbsp;<html:text property="alcnome" size="60" maxlength="50"></html:text>&nbsp;
						<html:submit>Pesquisar</html:submit>
					</td>
				</tr>
				<logic:present name="ls_alunos">
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						C.P.F.
					</th>
					<th>
						Data de Nascimento
					</th>
				</tr>
				<logic:iterate name="ls_alunos" id="b">
				<tr>
					<td>
						<bean:write name="b" property="alncodg"/>
					</td>
					<td>
						<a href="actionBoleto.do?m=lista&alncodg=<bean:write name="b" property="alncodg"/>">
							<bean:write name="b" property="alcnome"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="alccpf"/>
					</td>
					<td>
						<bean:write name="b" property="aldnasc"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:present>				
			
			</tbody>			
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>