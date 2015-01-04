<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>


<html:html>
<head>
	<title>
		<bean:message key="welcome.title"/>
	</title>
	<link rel="stylesheet" href="stylesheet/style.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="stylesheet/print.css" type="text/css" media="print">
	<link rel="shortcut icon" href="favicon.ico">	
	
</head>
	
<body style="background: url('imagens/background.gif');">
		
	<DIV STYLE="width: 50%; position: absolute; top: 25%; left: 25%;text-align: center;">
			
	<fieldset style="width: 350px;" >
		<legend class="red">Login do Usuário</legend>
		<html:errors/>	
		<html:form method="POST" action="/actionLogin" focus="login">
		<html:hidden property="m" value="autenticaUsuario"/>
		
		<table style="width: 80%; height: 30%; background-color: #FFFAFA;" border="0" align="center">
			<tr>
				<td>
					Login
				</td>
				<td>
					<html:text property="login" />
				</td>
			</tr>
			<tr>
				<td>
					Senha
				</td>
				<td>
					<html:password property="senha"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right;">
					<html:submit styleClass="btn">Entrar</html:submit>
				</td>
			</tr>
		</table>
		</html:form>
	</fieldset>
	</DIV>
</body>
</html:html>