	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	
	<script type="text/javascript">
	<!--
	
		function matriculaAluno(frm,tipo){
			if (tipo == 'A'){
				frm.m.value = 'matricular';
				frm.submit();
			}
			if (tipo == 'R'){
				frm.m.value = 'remover';
				frm.submit();
			}
		
		}
	//-->
	</script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Matrícula de Aluno    &nbsp;
			</legend>
			<html:form action="actionMatricula" focus="tmncodg">
			<input type="hidden" name="mtncodg" value="<bean:write name="matricula" property="mtncodg"/>">
			<html:hidden property="m" value="matricular"/>
			
			<table style="width: 600px; align: center;">
			<tbody>
				<tr>
					<td>
						<strong> Aluno:</strong> <bean:write name="matricula" property="mtcnmal"/><br>
						<strong> Curso:</strong> <bean:write name="matricula" property="mtcdccs"/><br>
						<strong> Semestre de Ingresso:</strong> <bean:write name="matricula" property="mtcdcsm"/><br>
						<strong> Situação da Matrícula:</strong> <bean:write name="matricula" property="mtcdcsr"/>
						
					</td>
				</tr>
				<tr>
					<th style="text-align: center;"> Disciplinas Disponíveis para a matrícula </th>
				</tr>
				<tr>
					<td>
						<select name="tmncodg" size="8" style="width: 600px;" ondblclick="matriculaAluno(this.form,'A');">
							<logic:iterate id="b" name="turmas_mat">
								<option value="<bean:write name="b" property="tmncodg"/>"><bean:write name="b" property="tmcdcdc"/> </option>								  
							</logic:iterate>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: center">
						<input type="button" value="Adcionar" onclick="matriculaAluno(this.form,'A');">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Remover" onclick="matriculaAluno(this.form,'R');">						
					</td>
				</tr>
				<tr>
					<th style="text-align: center;"> Disciplinas Escolhidas para a matrícula </th>
				</tr>
				<tr>
					<td>
						<select name="tmncodg2" size="8" style="width: 600px;" ondblclick="matriculaAluno(this.form,'R');">
							<logic:iterate id="b" name="turmas_mat_temp">
								<option value="<bean:write name="b" property="tmncodg"/>"><bean:write name="b" property="tmcdcdc"/> </option>								  
							</logic:iterate>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: center">
						<input type="button" value="Finalizar" onclick="if(window.confirm('Finalizar a matricula do aluno com as disciplinas escolhidas?')){ window.location = 'actionMatriculaTurma.do?m=finaliza&alncodg=<bean:write name="matricula" property="mtncgal"/>';}">
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