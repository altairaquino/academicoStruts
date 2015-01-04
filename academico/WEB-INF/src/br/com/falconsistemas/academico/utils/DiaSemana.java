package br.com.falconsistemas.academico.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class DiaSemana {
	
	private int valor;
	
	private String nome;
	
	public DiaSemana(int valor, String nome) {
		super();
		this.valor = valor;
		this.nome = nome;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static List<DiaSemana> getDiaSemanas(){
		List<DiaSemana> diaSemanas = new ArrayList<DiaSemana>();
		//Calendar calendar = new GregorianCalendar(2008,4,16);
		Calendar calendar = new GregorianCalendar();
		for (int i = 0; i < 7; i++) {
			calendar.set(Calendar.DAY_OF_WEEK, (i+1));
			diaSemanas.add( 
				new DiaSemana(calendar.get(Calendar.DAY_OF_WEEK), 
						calendar.getDisplayName(
								Calendar.DAY_OF_WEEK, 
								Calendar.SHORT, 
								Locale.getDefault()
							).toLowerCase()
					)
				);
		}
		return diaSemanas;
	}
	public static void main(String [] args){
		List<DiaSemana> diaSemanas = getDiaSemanas();
		for (DiaSemana diaSemana : diaSemanas) {
			System.out.println(diaSemana.getValor() + "" + diaSemana.getNome());
		}
	}
}
