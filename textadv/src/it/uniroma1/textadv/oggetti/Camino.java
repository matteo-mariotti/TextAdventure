package it.uniroma1.textadv.oggetti;

public class Camino extends OggettoCheInteragisce{

	boolean acceso = true;
	
	public Camino(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	public Camino(String nome) {
		super(nome);
	}
	
	public String toString() {
		return "Il camino è " + (acceso ? "acceso" : "spento") + " e contiene: " + super.interazione; 
	}
	
	
}
