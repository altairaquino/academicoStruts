package br.com.falconsistemas.academico.struts.model;

import java.sql.Connection;
import java.sql.DriverManager;


public final class Banco {
	
	private static Connection con = null;
	
	public static Connection getConnection(){
		if(con == null){
			try{				
				Class.forName("org.firebirdsql.jdbc.FBDriver");
				con = DriverManager.getConnection("jdbc:firebirdsql:localhost:academico?defaultResultSetHoldable=True","SYSDBA","masterkey");				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return con;
	}	
	
}
