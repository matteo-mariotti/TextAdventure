package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;

/**
 * Classe che modella un generico oggetto nella stanza
 * 
 * @author matte
 *
 */
public abstract class Oggetto extends ElementoStanza {
	/**
	 * Costruttore della classe
	 * 
	 * @param nome Nome dell'oggetto
	 */
	public Oggetto(String nome) {
		super(nome);
	}

	/**
	 * Metodo che permette di ottenere il nome dell'oggetto
	 */
	public String getNome() {
		return nome;
	}

}
