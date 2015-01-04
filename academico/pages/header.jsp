
<table>
	<tbody>
		<tr>
			<th style="width:10%;height:10%">
				<img class="avatar" src="imagens/logo_iesl.jpg" width="80" height="80"/>
			</th>
			<td style="vertical-align:top">
				<div id="usuario">
					<strong>
						Usuário:&nbsp;<bean:write name="usuario" property="uscnome"/> 
						<br/><br>
						<bean:write name="entidade" property="encdesc"/>
					</strong>
				<div>
				<br>
				<div id="agora">
					<%= new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm").format(new java.util.Date()) %>
				</div>
			</td>
		</tr>
	</tbody>
</table>