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
			<html:form action="actionBoleto" method="post" onsubmit="return window.confirm('Confirmar a baixa do Boleto com os dados fornecidos?');">
			<html:hidden property="blncodg"/>
			<html:hidden property="blncgal"/>
			<html:hidden property="m" value="baixar2"/>
			<input type="hidden" name="blnusbx" value="<bean:write name="usuario" property="usncodg"/>">
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2"> &nbsp;</th>
				</tr>		
				<tr>
					<td style="width: 25%">
						<b>Aluno</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="formBoleto" property="blcnmal"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%;">
						<b>C.P.F.</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="formBoleto" property="blccpf"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Tipo de Boleto</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="formBoleto" property="blcdctb"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Nosso Número</b>
					</td>
					<td style="width: 75%; padding: 5px;">
						<bean:write name="formBoleto" property="blnnnum"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%;">
						<b>Vencimento</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="formBoleto" property="bldvenc"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Valor</b>
					</td>
					<td style="width: 75%; padding: 5px;"> 
						<bean:write name="formBoleto" property="blyvalr"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Tarifa Cobrada</b>
					</td>
					<td style="width: 75%; padding: 5px;">
						<html:text property="blytari" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>						
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						<b>Data do Pagamento</b>
					</td>
					<td style="width: 75%; padding: 5px;">
						<html:text property="bldpgto" maxlength="10" size="11" onkeyup="criaMascara(this, '##/##/####')"></html:text>						
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
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