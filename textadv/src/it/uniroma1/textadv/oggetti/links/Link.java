package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.rooms.Room;

public abstract class Link extends Oggetto implements Openable {

	Room stanza1;
	Room stanza2;
	boolean bChiuso = false;
	Oggetto chiave = null;

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
		lock();
		this.chiave = chiave;
	}

	public String toString() {
		return super.nome // + "\t" + stanza1 + "\t"+ stanza2
		;
	}

	public void open() {
		if (!(bChiuso)) 
			System.out.println("" + getNome() + " era già aperto");
		else if(chiave == null)
		{
			bChiuso = false;
			System.out.println("" + getNome() + " è stato aperto");}
		else {
			System.out.println("" + getNome() + " è ancora chiuso");
		}
	}

	
	public void lock() {
		bChiuso = true;
	}
	
	public boolean unlock(Oggetto chiave) {
		if (this.chiave == chiave)
		{
			this.chiave = null;
			return true;
		}else if (chiave == null) {
			bChiuso = false;
			return true;}
		return false;
	}
	public void open(Oggetto chiave) {
		if (unlock(chiave))
			open();
		else
			System.out.println("L'oggetto fornito non è in grado di aprire " + nome);
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
