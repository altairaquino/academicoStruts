<ul class="main_menu">
	<li>
		<a href="home.do" title="P�gina Inicial">P�gina inicial</a>
	</li>		
		
	<%= ((String)session.getAttribute("menu")) %>	
		
	<li>
		<a href="usuarioAlteraSenha.do" title="Trocar senha">Trocar senha</a>
	</li>
	<li>
		<a href="logout.do" title="Sair do sistema">Sair do sistema</a>
	</li>
</ul>