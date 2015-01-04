	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend>Semestres</legend>
				<table>
					<tbody>
					<tr>
						<th>
							Semestre
						</th>
						<th>
							Data Início
						</th>
						<th>
							Data Fim
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_semestre">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="smcdesc"/>
						</td>
						<td>
							<bean:write name="b" property="smdaber"/>
						</td>
						<td>
							<bean:write name="b" property="smdfech"/>
						</td>
						<td>
							<input type="button" value="Detalhes" onclick="window.location = 'actionSemestre.do?m=opcoesSemestre&smncodg=<bean:write name="b" property="smncodg"/>'">
						</td>
					</tr>
					</logic:iterate>
					</tbody>
				</table>
			
			</fieldset>
			
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>