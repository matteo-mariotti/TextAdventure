package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.verbs.Prendi;

public class Teletrasporto extends Link {

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
