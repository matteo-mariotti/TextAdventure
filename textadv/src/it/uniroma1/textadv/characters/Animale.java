package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.oggetti.Takeable;

/**
 * Classe che rappresenta un generico animale
 * 
 * @author matte
 *
 */
public abstract class Animale extends Entita implements Takeable {

	/**
	 * Costruttore della classe
	 * 
	 * @param nome Nome dell'animale
	 */
	public Animale(String nome) {
		super(nome);
	}

	/**
	 * Metodo astratto che impone agli animali di essere "accarezzabili"
	 * 
	 * @return Verso dell'animale quando viene accarezzato
	 */
	public abstract String accarezza();

}
