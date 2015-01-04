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
				&nbsp;   Extrato de Pendências do Aluno    &nbsp;
			</legend>
			<table>
			<tbody>
				<tr>
					<th colspan="8">
						Aluno: <bean:write name="aluno" property="alcnome"/><br> CPF: <bean:write name="aluno" property="alccpf"/> 
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<h3>Extrato</h3>
					</td>
				</tr>
				<tr>
					<th>
						Data Venc.
					</th>
					<th>
						Descrição
					</th>
					<th>
						Valor
					</th>
					<th>
						Valor Pago
					</th>
					<th>
						Situação
					</th>
					<th>
						Data Pgto
					</th>
					<th>
						Número
					</th>
					<th>
						Estado
					</th>
					<th>
						Boleto
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_boletos">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="bldvenc"/>
					</td>
					<td>
						<bean:write name="b" property="blcdctb"/>
					</td>
					<td>
						R$ <bean:write name="b" property="blyvalr"/>
					</td>
					<td>
						<logic:notEmpty name="b" property="bldpgto">
							R$ <bean:write name="b" property="blycobr"/>
						</logic:notEmpty>						
					</td>
					<td>
						<logic:empty name="b" property="bldpgto">
							A pagar
						</logic:empty>						
						<logic:notEmpty name="b" property="bldpgto">
							Pago
						</logic:notEmpty>
					</td>
					<td>
						<bean:write name="b" property="bldpgto"/>
					</td>
					<td>
						<bean:write name="b" property="blnnnum"/>
					</td>
					<td>
						<logic:equal name="b" property="bllativ" value="T">
							Ativo
						</logic:equal>
						<logic:equal name="b" property="bllativ" value="F">
							Inativo
						</logic:equal>						
					</td>
					<td>
						<% if (((BeanBoleto)b).getBldpgto() == null && ((BeanBoleto)b).getBllativ().equals("T")){ %>
							<input type="image" title="Imprimir Boleto" src="imagens/print.gif" onclick="window.location = 'actionBoleto.do?m=imprimir&blncodg=<bean:write name="b" property="blncodg"/>';">&nbsp;
							<input type="image" title="Editar Boleto" src="imagens/editar.gif" onclick="window.location = 'actionBoleto.do?m=editar&blncodg=<bean:write name="b" property="blncodg"/>';">&nbsp;
							<logic:present name="baixaboleto" scope="session">
								<input type="image" title="Desativar Boleto" src="imagens/fecha.png" onclick="if (window.confirm('Deseja desativar o boleto?')) {window.location = 'actionBoleto.do?m=desativa&blncodg=<bean:write name="b" property="blncodg"/>';}">&nbsp;
								<input type="button" value="Baixar" onclick="window.location = 'actionBoleto.do?m=montaBaixa&blncodg=<bean:write name="b" property="blncodg"/>';">
							</logic:present>	
						<% }else{ %>
							Indisponível
						<% } %>						
					</td>
				</tr>				
				</logic:iterate>
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
					<th colspan="8">
						* Valor de rerefencia, sem multa e juros.
					</th>
				</tr>
				<tr>
					<th colspan="8">
						Obs: O saldo remanescente não leva em consideração as parcelas com estado inativo.
					</th>
				</tr>
				<tr>
					<th colspan="8">
						<input type="button" name="back" value="Voltar" onclick="window.location = 'alunoPesquisaBoleto.do'"> &nbsp;&nbsp; 
						<input type="button" value="Imprimir Carnê" onclick="window.location = 'actionBoleto.do?m=imprimirCarneAluno&aluno=<bean:write name="aluno" property="alncodg"/>'"> 
					</th>
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