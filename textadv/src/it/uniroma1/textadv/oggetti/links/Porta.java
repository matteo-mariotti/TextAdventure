package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.rooms.Room;
/**
 * Classe che modella una generica porta
 * @author matte
 *
 */
public class Porta extends Link{

	/**
	 * Costruttore della classe
	 * @param nome Nome della porta
	 * @param stanzaPartenza Stanza 1
	 * @param stanzaDestinazione Stanza 2
	 */
	public Porta(String nome, Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome, stanzaPartenza, stanzaDestinazione);
		//Di default tutte le porte sono chiuse
		super.lock();
	}
}
