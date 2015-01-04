package br.com.falconsistemas.academico.struts.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.falconsistemas.academico.struts.bean.BeanParcelaCursoSemestre;
import br.com.falconsistemas.academico.utils.Utils;

public class ModelParcelaCursoSemestre {

	public static ModelParcelaCursoSemestre getInstance(){
		return new ModelParcelaCursoSemestre();
	}
	
	public ArrayList<BeanParcelaCursoSemestre> getParcelasCursoSemestre(int cmncodg){
		ArrayList<BeanParcelaCursoSemestre> parcelas = new ArrayList<BeanParcelaCursoSemestre>();
		try {
			String sql = " SELECT * FROM VW_PARCELACURSOSEM where PCNCGCM = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cmncodg);
			
			parcelas.addAll(Utils.getObjectsStr(st, BeanParcelaCursoSemestre.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return parcelas;
		
	}
	
	public BeanParcelaCursoSemestre getParcelaCursoSemestre(int pcncodg){
		BeanParcelaCursoSemestre parcela = null;
		try {
			String sql = " SELECT * FROM VW_PARCELACURSOSEM where PCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pcncodg);
			
			List<BeanParcelaCursoSemestre> l = Utils.getObjectsStr(st, BeanParcelaCursoSemestre.class); 
			
			if (!l.isEmpty())
				parcela = l .get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return parcela;
	}

	public void inserir(BeanParcelaCursoSemestre beanParcelaCursoSemestre) {
		try {
			String sql = " INSERT INTO PARCELACURSOSEM(PCNCGCM,PCDVENC,PCDJURS,PCDMULT)" +
					     " VALUES(?,?,?,?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanParcelaCursoSemestre.getPcncgcm()));
			st.setDate(2, Utils.stringToDateSQL(beanParcelaCursoSemestre.getPcdvenc()));
			st.setDate(3, Utils.stringToDateSQL(beanParcelaCursoSemestre.getPcdjurs()));
			st.setDate(4, Utils.stringToDateSQL(beanParcelaCursoSemestre.getPcdmult()));
						
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanParcelaCursoSemestre beanParcelaCursoSemestre) {
		try {
			String sql = " UPDATE PARCELACURSOSEM SET PCNCGCM = ?,PCDVENC = ?,PCDJURS = ?,PCDMULT = ?" +
					     " WHERE PCNCODG = ? ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanParcelaCursoSemestre.getPcncgcm()));
			st.setDate(2, Utils.stringToDateSQL(beanParcelaCursoSemestre.getPcdvenc()));
			st.setDate(3, Utils.stringToDateSQL(beanParcelaCursoSemestre.getPcdjurs()));
			st.setDate(4, Utils.stringToDateSQL(beanParcelaCursoSemestre.getPcdmult()));
			st.setInt(5, Integer.parseInt(beanParcelaCursoSemestre.getPcncodg()));
						
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void emitirParcela(int pcncodg) {
		try {
			String sql = "EXECUTE PROCEDURE SP_EMITIR_PARCELA(?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pcncodg);
								
			st.executeUpdate();
			
			System.out.println("PArcela emitida");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}