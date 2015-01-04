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
				&nbsp; Semestres > <bean:write name="semestre" property="smcdesc"/> > Editar Curso do Semestre    &nbsp;
			</legend>
			<html:errors/>			
			<html:form action="/actionCursoSemestre" focus="cmncgcs">
			<html:hidden property="m" value="update"/>
			<html:hidden property="cmncodg"/>
			<html:hidden property="cmncgsm"/>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Editar Curso do Semestre
					</th>
				</tr>
				<tr>
					<td>
						Curso
					</td>
					<td>
						<html:select property="cmncgcs" disabled="true" style="width: 200px;">
							<html:optionsCollection name="cursos" label="cscdesc" value="csncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Unidade de Ensino
					</td>
					<td>
						<html:select property="cmncgue" style="width: 200px;">
							<html:optionsCollection name="unidades" label="uecdesc" value="uencodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Turno
					</td>
					<td>
						<html:select property="cmncgtn" style="width: 100px;">
							<html:optionsCollection name="turnos" label="tncdesc" value="tnncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Valor Mensal
					</td>
					<td>
						<html:text property="cmyvalr" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Multa por Atrazo(%)
					</td>
					<td>
						<html:text property="cmymult" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Juros por Atrazo(%)
					</td>
					<td>
						<html:text property="cmyjuro" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Percentual Desconto
					</td>
					<td>
						<html:text property="cmydesc" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" name="back" value="Cancelar" onclick="window.location = 'actionCursoSemestre.do?m=opcoesCursoSemestre&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>';">&nbsp;&nbsp;
						<html:submit>Atualizar Curso</html:submit>
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