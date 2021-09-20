package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.oggetti.GiaAperto;
import it.uniroma1.textadv.rooms.Room;
/**
 * Classe che modella un bus
 * @author matte
 *
 */
public class Bus extends Link{

	/**
	 * Costruttore della classe bus
	 * @param nome Nome del bus
	 * @param stanzaPartenza Stanza di partenza
	 * @param stanzaDestinazione Stanza di arrivo
	 */
	public Bus(String nome,Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome, stanzaPartenza, stanzaDestinazione, new GiaAperto());
	}

}
