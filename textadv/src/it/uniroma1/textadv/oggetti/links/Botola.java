package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che modella una botola che permette il passaggio tra due stanze
 * 
 * @author matte
 *
 */
public class Botola extends Link {

	/**
	 * Costruttore della classe
	 * @param nome Nome del collegamento
	 * @param stanzaPartenza Stanza da cui si parte
	 * @param stanzaDestinazione Stanza in cui si va
	 */
	public Botola(String nome, Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome, stanzaPartenza, stanzaDestinazione);
	}

}
