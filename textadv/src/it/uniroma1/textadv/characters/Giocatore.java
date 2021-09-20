package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.oggetti.Cibo;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che modella il giocatore
 * 
 * @author matte
 *
 */
public class Giocatore extends Personaggio {

	/**
	 * Riferimento statico all'unico giocatore esistente nel gioco
	 */
	private static Giocatore player;
	/**
	 * Stanza in cui si trova il giocatore attualmente
	 */
	private Room stanzaCorrente;

	/**
	 * Costruttore del giocatore
	 * 
	 * @param nome   Nome del giocatore
	 * @param stanza Stanza in cui si trova inizialmente
	 */
	private Giocatore() {
		super(null);
	}

	/**
	 * Metodo che permette di ottenere un riferimento al giocatore
	 * 
	 * @return Unica istanza del giocatore
	 */
	public static Giocatore instanceOf() {
		if (player == null)
			player = new Giocatore();
		return player;
	}

	/**
	 * Metodo per ottenere la stanza in cui si trova il giocatore
	 * 
	 * @return Stanza corrente
	 */
	public Room getStanza() {
		return stanzaCorrente;
	}

	/**
	 * Metodo per impostare la stanza in cui si trova il giocatore
	 * 
	 * @param stanza Stanza in cui spostare il giocatore
	 * @return Stringa con il risultato dell'operazione
	 */
	public String setRoom(Room stanza) {
		stanzaCorrente = stanza;
		return "Ti trovi ora in " + stanza.getNome();
	}
	/**
	 * Metodo che permette al giocatore di mangiare
	 * @param cibo Cibo da mangiare
	 * @return Stringa con il risultato dell'operazione
	 */
	public String mangia(Cibo cibo) {
		Giocatore.instanceOf().getStanza().remove(cibo);
		return "Gnam! Hai mangiato: " + cibo.getNome();
	}
}
