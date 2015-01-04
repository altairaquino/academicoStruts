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
				&nbsp;   Edição de Dados do Boleto    &nbsp;
			</legend>
			<html:form action="actionBoleto" focus="bldvenc">
			<html:hidden property="m" value="update"/>
			<html:hidden property="blncodg"/>
			<html:hidden property="blncgal"/>
			<table style="width: 450px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDD;">
						Dados do Boleto
					</th>
				</tr>
				<tr>
					<td>
						Aluno
					</td>
					<td>
						<bean:write name="formBoleto" property="blcnmal"/>
					</td>
				</tr>
				<tr>
					<td>
						Valor
					</td>
					<td>
						<html:text property="blyvalr" size="10" maxlength="10" style="text-align: right;" onkeydown="Formata(this,10,event,2)"/>
					</td>
				</tr>
				<tr>
					<td>
						Vencimento
					</td>
					<td>
						<html:text property="bldvenc" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
					</td>
				</tr>				
				<tr>
					<td colspan="2" style="text-align: right;">
						<html:submit>Salvar Boleto</html:submit>
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