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
	public String rompi(Oggetto ogg) {
		if(bBroken){
			return "Il salvadanaio è gia rotto";
		}
		else if (ogg instanceof Martello) {
			bBroken = true;
			Giocatore.instanceOf().getStanza().addElementi(super.interazione);	
			return "Crash!! Hai rotto il salvadanaio";
		}
		return "Hai bisogno di un martello per rompere il salvadanaio";
	}
	
	@Override
	public String describe() {
		return bBroken ? "Il salvadanaio è rotto" : "Questo è un salvadanaio";
	}

	@Override
	public String rompi() {
		if (bBroken)
			return "Il salvadanaio è già rotto";
		else
			return "Per rompere il salvadanaio hai bisogno di un oggetto adatto";
	}
	
	

}
