package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.oggetti.GiaAperto;
import it.uniroma1.textadv.rooms.Room;
/**
 * Classe che modella una scala
 * @author matte
 *
 */
public class Scala extends Link {
	/**
	 * Costruttore della classe
	 * @param nome Nome della scala
	 * @param stanzaPartenza Stanza di partenza
	 * @param stanzaDestinazione Stanza di destinazione
	 */
	public Scala(String nome, Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome, stanzaPartenza, stanzaDestinazione, new GiaAperto());
	}

}
