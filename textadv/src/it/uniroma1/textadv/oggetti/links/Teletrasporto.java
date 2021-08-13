package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.verbs.Prendi;

public class Teletrasporto extends Link {

	public Teletrasporto(String nome,Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome, stanzaPartenza, stanzaDestinazione);
	}

	@Override
	public void open(Oggetto chiave) {
		super.open(chiave);
		new Prendi().esegui(this.getNome());
	}


}
