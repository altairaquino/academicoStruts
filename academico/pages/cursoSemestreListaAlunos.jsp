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
			<html:errors/>
			<table width="550">
			<tbody>
				<tr>
					<th colspan="3"> 
						Relação de alunos matriculados neste curso
					</th>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						CPF
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_aluno">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="alncodg"/>
					</td>
					<td>
						<bean:write name="b" property="alcnome"/>
					</td>
					<td>
						<bean:write name="b" property="alccpf"/>
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" name="back" value="Voltar" onclick="window.location = 'actionCursoSemestre.do?m=opcoesCursoSemestre&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>';">&nbsp;
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