package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.oggetti.Takeable;

/**
 * Classe che rappresenta un generico animale
 * 
 * @author matte
 *
 */
public abstract class Animale extends Entita implements Takeable {

	private VersoAnimale v;
	
	/**
	 * Costruttore della classe
	 * @param nome Nome dell'animale
	 * @param v Verso
	 * @param cp Comportamento quando parla
	 */
	Animale(String nome, VersoAnimale v, ComportamentoParla cp) {
		super(nome, cp);
		this.v = v;
	}
	
	/**
	 * Metodo per accarezzare un animale
	 * @return Verso dell'animale
	 */
	public String accarezza() {
		return v.verso();
	}
	

}
