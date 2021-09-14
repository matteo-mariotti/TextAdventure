package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.verbs.Prendi;

/**
 * Classe che modella l'oggetto teletrasporto del gioco
 * @author matte
 *
 */
public class Teletrasporto extends Link {
	
	/**
	 * Costruttore della classe
	 * @param nome Nome del teletrasporto
	 * @param stanzaPartenza Stanza di partenza
	 * @param stanzaDestinazione Stanza di destinazione
	 */
	public Teletrasporto(String nome,Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome, stanzaPartenza, stanzaDestinazione);
	}

	@Override
	public String open() {
		if (!(bChiuso))
			return new Prendi().esegui(this.getNome());
		return "Ti serve una chiave per usare il teletrasporto";
	}


}
