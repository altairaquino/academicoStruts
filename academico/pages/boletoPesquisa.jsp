	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		
		<fieldset>
			<legend>
				&nbsp;   Pesquisa Contrato - Dados do Contrato   &nbsp;
			</legend>
			<html:errors/>
			<br><br><br><br><br><br>
			<center>
			<html:form action="actionBoleto" focus="blnnnum">
			<html:hidden property="m" value="pesquisa"/>
			<table style="width: 250px;">			
			<tbody>
				<tr>
					<th style="text-align: center; background-color: #DDD;"> 
						Pesquisa Boleto
					</th>
				</tr>	
				<tr>
					<td>
						Nosso Número <html:text property="blnnnum" size="20" maxlength="18" onkeyup="criaMascara(this,'##################')"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<html:submit>Pesquisa</html:submit>					
					</td>
				</tr>			
			</tbody>			
			</table>
			</html:form>
			</center>	
			<br><br><br>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>