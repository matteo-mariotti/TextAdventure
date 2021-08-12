package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.characters.Giocatore;

public class Salvadanaio extends OggettoCheInteragisce implements Breakable{

	private boolean bBroken = false;
	
	public Salvadanaio(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	public Salvadanaio(String nome) {
		super(nome);
	}

	@Override
	public void rompi(Oggetto ogg) {
		if(bBroken){
			System.out.println("Il salvadanaio è gia rotto");
		}
		else if (ogg instanceof Martello) {
			System.out.println("Crash!! Hai rotto il salvadanaio");
			bBroken = true;
			Giocatore.instanceOf().getStanza().addElementi(super.interazione);	
		}
	}
	
	@Override
	public String describe() {
		return bBroken ? "Il salvadanaio è rotto" : "Questo è un salvadanaio";
	}

	@Override
	public void rompi() {
		if (bBroken)
			System.out.println("Il salvadanaio è già rotto");
		else
			System.out.println("Per rompere il salvadanaio hai bisogno di un oggetto");
	}
	
	

}
