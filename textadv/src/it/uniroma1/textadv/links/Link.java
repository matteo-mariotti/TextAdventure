package it.uniroma1.textadv.links;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.rooms.Room;

public abstract class Link extends Oggetto implements Openable{

	Room stanza1;
	Room stanza2;
	boolean bChiuso = false;
	Oggetto chiave;
	
	public Link(String nome, Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome);
		stanza1 = stanzaPartenza;
		stanza2 = stanzaDestinazione;
	}
	
	public Room getConnection(String startingRoom) {
		if (stanza1.getNome().equals(startingRoom))
			return stanza2;
		return stanza1;
	}
	
	public boolean status() {
		return bChiuso;
	}
	
	public void setClosed(Oggetto chiave) {
		bChiuso = true;
		this.chiave = chiave;
	}
	
	public String toString() {
		return super.nome //+ "\t" + stanza1 + "\t"+ stanza2
				;
	}
	
	public void open(Oggetto chiave) {
		if (this.chiave == chiave)
			bChiuso = false;
	}
	
	public void move() {
		if (bChiuso)
			if (Giocatore.instanceOf().getStanza().equals(stanza1)) 
				Giocatore.instanceOf().setRoom(stanza2);
			else
				Giocatore.instanceOf().setRoom(stanza1);
		else
			System.out.println("Questo collegamento è chiuso!");
	}
	
}
