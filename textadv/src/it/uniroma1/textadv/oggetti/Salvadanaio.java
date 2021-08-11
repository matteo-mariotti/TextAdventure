package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.characters.Giocatore;

public class Salvadanaio extends OggettoCheInteragisce implements Breakable{

	private boolean bBroke = false;
	
	public Salvadanaio(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	public Salvadanaio(String nome) {
		super(nome);
	}

	@Override
	public void azione() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rompi(Oggetto ogg) {
		if (ogg instanceof Martello) {
			System.out.println("Crash!! Hai rotto il salvadanaio");
			Giocatore.instanceOf().getStanza().addElementi(super.interazione);	
		}
	}
	
	@Override
	public String describe() {
		return bBroke ? "Il salvadanaio è rotto" : "Questo è un salvadanaio";
	}
	
	

}
