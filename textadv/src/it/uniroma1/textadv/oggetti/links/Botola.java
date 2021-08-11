package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.rooms.Room;

public class Botola extends Link{

	public Botola(String nome,Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome, stanzaPartenza, stanzaDestinazione);
	}

	@Override
	public void open() {
		if (super.status())
			System.out.println("La botola è ancora chiusa");
		else
			System.out.println("La botola è già aperta");
	}

}
