<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<logic:notPresent name="usuario" scope="session">
	<logic:redirect action="login.do"></logic:redirect>
</logic:notPresent>

<head>
	<title> 
		<bean:message key="welcome.title"/>
	</title>
	
	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
	<meta http-equiv="content-style-type" content="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta name="language" content="pt-br">
		
	<link rel="stylesheet" href="stylesheet/style.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="stylesheet/print.css" type="text/css" media="print">
	<link rel="shortcut icon" href="favicon.ico">
	
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="scripts/base.js"></script>
	<script type="text/javascript" src="scripts/validacao.js"></script>
	
	<script type="text/javascript">
		
		$(document).ready(function() {
			$('#hierarquias').hide();
		});
		
	</script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			basics();
		});		
				
	</script>
	

