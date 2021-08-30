package it.uniroma1.textadv.characters;

import java.util.HashMap;
import java.util.Map;
import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;

/**
 * Classe astratta che modella un generico personaggio
 * 
 * @author matte
 *
 */
public abstract class Personaggio extends Entita {

	/**
	 * Mappa che rappresenta l'inventario del giocatore
	 */
	private Map<String, ElementoStanza> inventario = new HashMap<>();

	/**
	 * Costruttore del personaggio
	 * 
	 * @param nome Nome del personaggio
	 */
	public Personaggio(String nome) {
		super(nome);
	}

	/**
	 * Metodo per ottenere l'inventario del giocatore
	 * 
	 * @return Mappa inventario del giocatore
	 */
	public Map<String, ElementoStanza> getInventario() {
		return inventario;
	}

	/**
	 * Metodo che permette di aggiungere oggetti all'inventario del giocatore
	 * @param ogg
	 * @return
	 */
	public String addOggetto(ElementoStanza ogg) {
		inventario.put(ogg.getNome(), ogg);
		return "Hai ottenuto: " + ogg.getNome();
	}

}
