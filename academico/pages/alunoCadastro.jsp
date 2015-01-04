<%@include file="topo.jsp" %>
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
		
			function preencheCidades(){				
				var uf = DWRUtil.getValue('alccnuf');
				Mapping.getCidades(retorno,uf,'alncdnc');							
			}
			
			function retorno(valor){
				DWRUtil.setValue("cidade",valor);
			}
			
			function preencheCidades2(){				
				var uf = DWRUtil.getValue('alcceuf');
				Mapping.getCidades(retorno2,uf,'alncded');						
			}
			
			function retorno2(valor){
				DWRUtil.setValue("cidade2",valor);
			}
			
			function init(){			
				DWRUtil.useLoadingMessage("Carregando Aguarde!!");
			}
			
			if (window.addEventListener) {
				window.addEventListener("load",init,true);
			}else if (window.attachEvent) {
				window.attachEvent("onload", init);
			}else {
				window.onload = init;
			}	
			
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
				&nbsp;   Cadastro de Aluno    &nbsp;
			</legend>
			<html:form method="post" action="/actionAluno" focus="alcnome">
			<html:hidden property="m" value="salvarAluno"/>
			<table align="center" width="500">
			<tbody>
				<tr>
					<td>
						Nome
					</td>
					<td colspan="2">
						<html:text property="alcnome" size="60" maxlength="55"/>
					</td>
				</tr>
				<tr>
					<td>
						C.P.F.
					</td>
					<td colspan="2">
						<html:text property="alccpf" size="15" maxlength="14" onkeyup="criaMascara(this, '###.###.###-##');"/>
					</td>
				</tr>
				<tr>
					<td>
						Sexo
					</td>
					<td colspan="2">
						<html:radio property="alcsexo" value="F">&nbsp;Feminino</html:radio> &nbsp;&nbsp;&nbsp;
						<html:radio property="alcsexo" value="M">&nbsp;Masculino</html:radio>
					</td>
				</tr>
				<tr>
					<td>
						Data de Nascimento
					</td>
					<td colspan="2">
						<html:text property="aldnasc" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
					</td>
				</tr>
				<tr>
					<td>
						Naturalidade
					</td>
					<td>
						<html:select property="alccnuf" styleId="alccnuf" onblur="preencheCidades()">
							<html:optionsCollection name="ls_estados" label="cdcuf" value="cdcuf"/>
						</html:select>
					</td>
					<td>
						<div id="cidade">
							<html:select property="alncdnc" style="width: 200px;">
								<option value="-1">Escolha o estado</option>
								<html:optionsCollection name="ls_cidades" label="cdcdesc" value="cdncodg"/>
							</html:select>							
						</div>						
					</td>
				</tr>
				<tr>
					<td>
						Nome do Pai
					</td>
					<td colspan="2">
						<html:text property="alcpai" size="60" maxlength="55"/>
					</td>
				</tr>
				<tr>
					<td>
						Nome do Mãe
					</td>
					<td colspan="2">
						<html:text property="alcmae" size="60" maxlength="55"/>
					</td>
				</tr>
				<tr>
					<td>
						Estado Civil
					</td>
					<td colspan="2">
						<html:select property="alncgec">
							<html:optionsCollection name="ls_estadoscivis" label="eccdesc" value="ecncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						R.G.
					</td>
					<td colspan="2">
						<html:text property="alcrg" size="11" maxlength="10" onkeyup="criaMascara(this, '##########');"/>
						Org.Exp.&nbsp;<html:text property="alcoerg" size="3" maxlength="5"/>&nbsp;
						U.F.&nbsp;<html:select property="alcufrg">
							<html:optionsCollection name="ls_estados" label="cdcuf" value="cdcuf"/>
						</html:select>
						Data<html:text property="alddete" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
					</td>
				</tr>
				<tr>
					<td>
						Título Eleitoral
					</td>
					<td colspan="2">
						<html:text property="alctiel" size="12" maxlength="12" onkeyup="criaMascara(this, '############');"/>
						Zona<html:text property="alczote" size="4" maxlength="3" onkeyup="criaMascara(this, '###');"/>
						Seção<html:text property="alcsete" size="4" maxlength="3" onkeyup="criaMascara(this, '###');"/>						
					</td>
				</tr>
				<tr>
					<td>
						Inst. Ens. Médio
					</td>
					<td colspan="2">
						<html:text property="alciesg" size="60" maxlength="90"/>
					</td>
				</tr>				
				<tr>
					<td>
						Nacionalidade
					</td>
					<td colspan="2">
						<html:text property="alcnaci" size="30" maxlength="30" value="BRASILEIRA"/>
					</td>
				</tr>
				<tr>
					<td>
						Contatos
					</td>
					<td colspan="2">
						Fone Fixo<html:text property="alcfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
						Celular<html:text property="alccell" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
					</td>
				</tr>
				<tr>
					<td>
						Endereço
					</td>
					<td colspan="2">
						<html:select property="alncgtl">
							<html:optionsCollection name="ls_logradouro" label="tlcdesc" value="tlncodg"/>
						</html:select>
						<html:text property="alclogr" size="42" maxlength="58"/>
					</td>
				</tr>
				<tr>
					<td>
						Complemento
					</td>
					<td colspan="2">
						<html:text property="alccomp" size="30" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<td>
						Estado/Cidade
					</td>
					<td>
						<html:select property="alcceuf" styleId="alcceuf" onblur="preencheCidades2()">
							<html:optionsCollection name="ls_estados" label="cdcuf" value="cdcuf"/>
						</html:select>
					</td>
					<td>
						<div id="cidade2">
							<html:select property="alncded" style="width: 200px;">
								<option value="-1">Escolha o estado</option>
								<html:optionsCollection name="ls_cidades" label="cdcdesc" value="cdncodg"/>
							</html:select>							
						</div>						
					</td>
				</tr>
				<tr>
					<td>
						C.E.P.
					</td>
					<td colspan="2">
						<html:text property="alccep" size="9" maxlength="9" onkeyup="criaMascara(this, '#####-###');"/>&nbsp;&nbsp;
						Bairro&nbsp;<html:text property="alcbair" size="40" maxlength="30"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="button" value="Cancelar" onclick="window.location = 'home.do'" class="btn_hot">&nbsp;&nbsp;
						<html:submit styleClass="btn">Salvar</html:submit>
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