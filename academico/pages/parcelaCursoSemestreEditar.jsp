<%@include file="topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript">
<!--
	function copiaDatas(elem){
		document.getElementById('pcdmult').value = elem.value;
		document.getElementById('pcdjurs').value = elem.value;
	}
	
//-->
</script>
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				<bean:write name="semestre" property="smcdesc"/> > <bean:write name="cursosemestre" property="cmcdccs"/> (<bean:write name="cursosemestre" property="cmcdcue"/>) > Editar Parcela do Curso
			</legend>
			<html:errors/>
			<html:form action="/actionParcelaCursoSemestre" focus="pcdvenc">
			<html:hidden property="m" value="update"/>
			<html:hidden property="pcncodg"/>
			<html:hidden property="pcncgcm"/>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Edição Parcela do Curso do Semestre
					</th>
				</tr>
				<tr>
					<td>
						Data de Vencimento
					</td>
					<td>
						<html:text property="pcdvenc" maxlength="10" size="11" onkeyup="criaMascara(this, '##/##/####');" onblur="copiaDatas(this)"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Data da Multa
					</td>
					<td>
						<html:text property="pcdmult" styleId="pcdmult" maxlength="10" size="11" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Data dos Juros
					</td>
					<td>
						<html:text property="pcdjurs" styleId="pcdjurs" maxlength="10" size="11" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit>Salvar Parcela</html:submit>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" name="back" value="Voltar" onclick="window.location = 'actionParcelaCursoSemestre.do?m=lista&cmncodg=<bean:write name="cursosemestre" property="cmncodg"/>';">
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