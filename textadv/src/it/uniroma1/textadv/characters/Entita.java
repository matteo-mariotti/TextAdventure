package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementoStanza;

/**
 * Classe che descrive una generica Entita del gioco
 * 
 * @author matte
 *
 */
public abstract class Entita extends ElementoStanza {
	
	private ComportamentoParla comportamentoParla;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param nome Nome dell'entità
	 */
	Entita(String nome, ComportamentoParla cp) {
		super(nome);
		this.comportamentoParla = cp;
	}

	/**
	 * Metodo che permette di parlare con una entita
	 * @return Stringa con la risposta dell'entita
	 */
	public String speak() {
		return comportamentoParla.speak();
	}

}
