	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	
	<script type="text/javascript">
<!--
	function ajustaValor(frm){
		if (frm.blydesc.value == ''){
			frm.blydesc.value = '0,00';
		}
		
		var valor = new Number(frm.blyvalr.value.replace(',','.'));
	 	var juros = new Number(frm.blyjurs.value.replace(',','.'));
	 	var multa = new Number(frm.blymult.value.replace(',','.'));
	 	var desconto = new Number(frm.blydesc.value.replace(',','.'));
		
		if (desconto <= valor){
			cobrado = valor - desconto + juros + multa;
			frm.blycobr.value = new String(cobrado).replace('.',',');
			return true;
		}else{
			alert('O valor do desconto não pode ser maior que o valor do documento!');
			return false;
		}
	
	}
	
	function validaForm(frm){		
		
		var valor = new Number(frm.blyvalr.value.replace(',','.'));
	 	var desconto = new Number(frm.blydesc.value.replace(',','.'));
		
		if (desconto <= valor){
			return true;
		}else{
			alert('O valor do desconto não pode ser maior que o valor do documento!');
			return false;
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
				&nbsp; Baixa de Pendência (Boleto)  &nbsp;
			</legend>
			<html:form action="actionBoleto" method="post" onsubmit="return validaForm(this)">
			<html:hidden property="blncodg"/>
			<html:hidden property="blncgal"/>
			<html:hidden property="m" value="baixar"/>
			<input type="hidden" name="blnusbx" value="<bean:write name="usuario" property="usncodg"/>">
			<table>
			<tbody>
				<tr>
					<th colspan="2"> &nbsp;</th>
				</tr>		
				<tr>
					<td style="width: 25%">
						<b>Aluno</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="boleto" property="blcnmal"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%;">
						<b>C.P.F.</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="boleto" property="blccpf"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Tipo de Boleto</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="boleto" property="blcdctb"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Número</b>
					</td>
					<td style="width: 75%; padding: 5px;">
						<bean:write name="boleto" property="blnnnum"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%;">
						<b>Vencimento</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="boleto" property="bldvenc"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Valor</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<html:text property="blyvalr" disabled="true" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Desconto</b>
					</td>
					<td style="width: 75%; padding: 5px;">
						<html:text property="blydesc" styleId="blydesc" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)" onblur="ajustaValor(this.form)"></html:text>
						<logic:present name="desconto">
							Desconto de <bean:write name="desconto"/>% para pagamento até a data de vencimento.
						</logic:present>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Juros</b>
					</td>
					<td style="width: 75%; padding: 5px;">
						<html:text property="blyjurs" styleId="blyjurs" disabled="true" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
						<logic:present name="juros">
							Juros de <bean:write name="juros"/>% ao mês a contar do vencimento.
						</logic:present>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Multa</b>
					</td>
					<td style="width: 75%; padding: 5px;">
						<html:text property="blymult" styleId="blymult" disabled="true" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
						<logic:present name="multa">
							Multa de <bean:write name="multa"/>% após o vencimento.
						</logic:present>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Valor Cobrado</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<html:text property="blycobr" styleId="blycobr" disabled="true" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>				
				<tr>
					<td style="padding: 6px;">
						<input type="button" value="Cancelar" onclick="window.location = 'actionBoleto.do?m=lista&alncodg=<bean:write name="boleto" property="blncgal"/>'" class="btn_hot">
					</td>
					<td>
						<html:submit styleClass="btn">Baixar Pendência</html:submit>
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