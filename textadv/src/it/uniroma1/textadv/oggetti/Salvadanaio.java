package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.characters.Giocatore;
/**
 * Classe che modella un salvadanaio
 * @author matte
 *
 */
public class Salvadanaio extends OggettoCheInteragisce implements Breakable{

	/**
	 * Flag per indicare se il salvadanaio è rotto oppure no
	 */
	private boolean bBroken = false;
	
	/**
	 * Costruttore della classe Salvadanaio
	 * @param nome Nome del salvadanaio
	 * @param inter Oggetto contenuto nel salvadanaio
	 */
	public Salvadanaio(String nome, Oggetto inter) {
		super(nome, inter);
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
		return bBroken ? "Il salvadanaio è già rotto" : "Per rompere il salvadanaio hai bisogno di un oggetto adatto";
	}
	
	

}
