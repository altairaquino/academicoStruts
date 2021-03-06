package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanUsuario;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelUsuario {
	
	public static ModelUsuario getInstance(){
		return new ModelUsuario();
	}
	
	public BeanUsuario getBeanUsuario(int usncodg){
		BeanUsuario usuario = null;
		try {
			String sql = " SELECT USNCODG, USCLOGN, USCPSWD, USCNOME, USLATIV FROM VW_USUARIO WHERE USNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, usncodg);
			List<BeanUsuario> l = Utils.getObjectsStr(st, BeanUsuario.class);
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public void alteraSenha(int usncodg, String novaSenha){
		try {
			String sql = " UPDATE USUARIO SET USCPSWD = ? WHERE USNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, novaSenha);
			st.setInt(2, usncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BeanUsuario getBeanUsuarioPorLogin(String usclogn){
		BeanUsuario usuario = null;
		try {
			String sql = "SELECT USNCODG, USCLOGN, USCPSWD, USCNOME, USLATIV FROM VW_USUARIO WHERE USCLOGN = UPPER(?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, usclogn);
			List<BeanUsuario> l = Utils.getObjectsStr(st, BeanUsuario.class);
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public boolean autenticaUsuario(String usclogn, String uscpswd){
		boolean ret = false;
		try {
			String sql = "SELECT USNCODG, USCLOGN, USCPSWD, USCNOME, USLATIV FROM VW_USUARIO WHERE USCLOGN = UPPER(?) AND USCPSWD = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, usclogn);
			st.setString(2, uscpswd);
			ret = !Utils.getObjectsStr(st, BeanUsuario.class).isEmpty();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	

}
