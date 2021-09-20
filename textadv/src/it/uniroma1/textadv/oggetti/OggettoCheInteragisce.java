package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;

/**
 * Classe che modella un oggetto che interagisce con un altro elemento della
 * stanza
 * 
 * @author matte
 *
 */
public abstract class OggettoCheInteragisce extends Oggetto {

	/**
	 * Interazione dell'oggetto
	 */
	ElementoStanza interazione;

	/**
	 * Costruttore della classe
	 * 
	 * @param nome  Nome dell'oggetto
	 * @param inter Elemento con cui interagisce
	 */
	OggettoCheInteragisce(String nome, ElementoStanza inter) {
		super(nome);
		interazione = inter;
	}

	/**
	 * Permette di verificare se l'oggetto possiede un'interazione o meno
	 * 
	 * @return True se l'interazione è presente, false altrimenti
	 */
	public boolean getInterazione() {
		return interazione == null ? false : true;
	}
	
	/**
	 * Permette di ottenere l'oggetto con cui si interagisce
	 * @return Oggetto interazione
	 */
	public ElementoStanza getOggInter() {
		return interazione;
	}

}
