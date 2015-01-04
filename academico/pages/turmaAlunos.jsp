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
				<bean:write name="semestre" property="smcdesc"/> > <bean:write name="cursosemestre" property="cmcdccs"/> (<bean:write name="cursosemestre" property="cmcdcue"/>) > <bean:write name="turma" property="tmcdcdc"/> > Alunos da Turma
			</legend>
			<html:form action="actionMatriculaTurma">
			<html:hidden property="m" value="updateNotas"/>
			<input type="hidden" name="ttncgcm" value="<bean:write name="cursosemestre" property="cmncodg"/>">
			<table width="450">
			<tbody>
				<tr>
					<th colspan="3"> Alunos da Turma </th>
				</tr>
				<tr>
					<td colspan="3"> &nbsp; </td>
				</tr>
				<logic:notEmpty name="ls_matriculaturma">
				<!-- 
				<tr>
					<td colspan="3" style="text-align: right;"> 
						<html:submit>Salvar Notas</html:submit>
					</td>
				</tr>
				 -->				
				<tr>
					<th>
						Código 
					</th>
					<th> 
						Nome 
					</th>
					<th>
						Nota
					</th>
					<th>
						Situação
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_matriculaturma">
				<html:hidden name="b" property="ttncodg"/>
				<html:hidden name="b" property="ttncgtm"/>
				<html:hidden name="b" property="ttncgmt"/>
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th>
						<bean:write name="b" property="ttncgal"/>
					</th>
					<th>
						<bean:write name="b" property="ttcnmal"/>
					</th>
					<th>
						<logic:equal name="b" property="ttnnota" value="0,00">
							<html:text property="ttnnota" value="" size="5" maxlength="5" style="text-align: right;" onkeydown="Formata(this,4,event,2)"/>
						</logic:equal>
						<logic:notEqual name="b" property="ttnnota" value="0,00">
							<html:text property="ttnnota" name="b" size="5" maxlength="5" style="text-align: right;" onkeydown="Formata(this,4,event,2)"/>
						</logic:notEqual>						
					</th>
					<th>
						<bean:write name="b" property="ttcdcst"/>
					</th>
				</tr>				
				</logic:iterate>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: right;"> 
						<html:submit>Salvar Notas</html:submit>
					</td>
				</tr>
				</logic:notEmpty>
				<tr>
					<td colspan="3">
						<input type="button" name="back" value="Voltar" onclick="window.location = 'actionTurma.do?m=lista&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>'">
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