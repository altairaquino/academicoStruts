package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanBoleto;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelBoleto {

	public static ModelBoleto getInstance(){
		return new ModelBoleto();
	}
	
	public ArrayList<BeanBoleto> getBoletosDoAluno(int alncodg){
		ArrayList<BeanBoleto> boletos = new ArrayList<BeanBoleto>();
		try {
			String sql = " SELECT * FROM VW_BOLETO WHERE BLNCGAL = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			boletos.addAll(Utils.getObjectsStr(st, BeanBoleto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return boletos;		
	}
	
	public BeanBoleto getBoleto(int blncodg){
		BeanBoleto religiao = null;
		try {
			String sql = " SELECT * FROM VW_BOLETO WHERE BLNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, blncodg);
			
			List<BeanBoleto> l = Utils.getObjectsStr(st, BeanBoleto.class); 
			
			if (!l.isEmpty())
				religiao = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return religiao;
		
	}
	
	
	public BeanBoleto getBoletoNossoNumero(int blnnnum){
		BeanBoleto boleto = null;
		try {
			String sql = " SELECT * FROM VW_BOLETO WHERE BLNNNUM = ? AND BLDPGTO IS NULL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, blnnnum);
			
			List<BeanBoleto> l = Utils.getObjectsStr(st, BeanBoleto.class); 
			
			if (!l.isEmpty())
				boleto = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return boleto;
		
	}
	
	public float getSomaBoletos(int alncodg){
		float soma = 0;
		try {
			String sql = " SELECT SUM(BLYVALR) SOMA FROM VW_BOLETO WHERE BLNCGAL = ? AND BLDPGTO IS NULL AND BLLATIV = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, alncodg);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				soma = rs.getFloat("SOMA");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return soma;
		
	}

	public void baixar(BeanBoleto beanBoleto) {
		
		try {
			String sql = " UPDATE BOLETO SET BLDPGTO = 'NOW'," +
					     " BLYCOBR = ?," +
					     " BLYDESC = ?," +
					     " BLYMULT = ?," +
					     " BLYJURS = ?," +
					     " BLNUSBX = ?"+
					     " WHERE BLNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(beanBoleto.getBlycobr())));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(beanBoleto.getBlydesc())));
			st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(beanBoleto.getBlymult())));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(beanBoleto.getBlyjurs())));
			st.setInt(5, Integer.parseInt(beanBoleto.getBlnusbx()));
			st.setInt(6, Integer.parseInt(beanBoleto.getBlncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void baixar2(BeanBoleto beanBoleto) {
		
		try {
			String sql = " UPDATE BOLETO SET BLDPGTO = ?," +
					     " BLYTARI = ?"+
					     " WHERE BLNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(beanBoleto.getBldpgto()));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(beanBoleto.getBlytari())));
			st.setInt(3, Integer.parseInt(beanBoleto.getBlncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(BeanBoleto boleto) {
		try {
			String sql = "UPDATE BOLETO SET BLDVENC = ?, BLYDOC = ?, BLYVALR = ? WHERE BLNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1, Utils.stringToDateSQL(boleto.getBldvenc()));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(boleto.getBlyvalr())));
			st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(boleto.getBlyvalr())));
			st.setInt(4, Integer.parseInt(boleto.getBlncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void dasativa(BeanBoleto boleto) {
		try {
			String sql = "UPDATE BOLETO SET BLLATIV = 'F' WHERE BLNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(boleto.getBlncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
