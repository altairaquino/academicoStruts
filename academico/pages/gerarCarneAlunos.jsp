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
				&nbsp;  Geração dos carnes dos Alunos   &nbsp;
			</legend>
			<table>
			<tbody>
				<tr>
					<th colspan="8">					
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<h3>Resumo de Parcelas Geradas</h3>
					</td>
				</tr>
				<tr>				
					<th>
						Unidade de Ensino
					</th>
					<th>
						Semestre
					</th>
					<th>
						Tipo boleto
					</th>
					<th>
						Parcela geradas
					</th>
					<th>
						Qtd alunos
					</th>
					<th>
						Boletos pagos
					</th>
					<th>						
					</th>
					<th>					
					</th>
				</tr>
				<% int c = 0; %>
				<logic:present name="resumoParcelas">
					<logic:iterate id="b" name="resumoParcelas">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="unidade"/>
						</td>
						<td>
							<bean:write name="b" property="semestre"/>
						</td>
						<td>
							<bean:write name="b" property="tipoboleto"/>
						</td>
						<td>
							<bean:write name="b" property="parcela"/>				
						</td>
						<td>
							<bean:write name="b" property="alunos"/>
						</td>
						<td>
							<bean:write name="b" property="pago"/>
						</td>
						<td>
							
						</td>
						<td>
											
						</td>
						<td>									
						</td>
					</tr>			
					</logic:iterate>
				</logic:present>
				<tr>
					<td colspan="8">
						&nbsp;
					</td>
				</tr>
				<logic:present name="soma">
				<tr>
					<th colspan="2">
						Saldo Remanescente*
					</th>
					<th>
						R$ <bean:write name="soma"/>
					</th>
					<th colspan="6">&nbsp;</th>
				</tr>
				</logic:present>
				<tr>
					<td>
						<input type="button" value="Gerar Carnê" onclick="window.location = 'actionBoleto.do?m=imprimirCarne';">
					</td>
				</tr>
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
<%@page import="br.com.falconsistemas.academico.struts.bean.BeanBoleto"%>
</html>