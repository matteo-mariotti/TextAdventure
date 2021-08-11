package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.rooms.Room;

public class Giocatore extends Personaggio{

	private static Giocatore player;
	private Room stanzaCorrente;	
	
	private Giocatore(String nome, Room stanza) {
		super(nome);
		stanzaCorrente = stanza;
	}
	
	public static Giocatore instanceOf() {
		if (player == null)
			player = new Giocatore("", null);
		return player;
		}

	public Room getStanza() {
		return stanzaCorrente;
	}
	
	public void setRoom(Room stanza) {
		stanzaCorrente = stanza;
	}
}
