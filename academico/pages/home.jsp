	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	</head>		
	<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>		

		<div id="content">
				<ul class="aviso" id="erros">
			</ul><!-- 
				<a onclick="window.location='/usuario/perfil/foto.do'">Alterar Foto</a> <br>
				<a onclick="window.location='/usuario/perfil/foto.do?perform=desativar'">Remover Foto</a>
				 -->
				<table align="center" border="0" cellpadding="0" cellspacing="0" height="356" width="750">
  				<tbody>
  					<tr align="center" bgcolor="#dededc" valign="middle">
    					<td align="left" height="356" valign="top" width="245">
    						<div align="left">
    						
    						</div>
       						<div align="left">
       							<img src="imagens/boasvindas.gif" height="356" width="245">
       						</div>
    					</td>
    					<td align="left" height="356" valign="top" width="490"><div align="left">
    					

						</td>
			  		</tr>
				</tbody>
				</table>
			</div>
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>