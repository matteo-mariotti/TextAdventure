package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.oggetti.ApriConChiave;
import it.uniroma1.textadv.oggetti.ApriSenzaChiave;
import it.uniroma1.textadv.oggetti.ComportamentoApertura;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che rappresenta un generico collegamento
 * 
 * @author matte
 *
 */
public abstract class Link extends Oggetto{

	/**
	 * Stanza di partenza
	 */
	private Room stanza1;
	/**
	 * Stanza destinazione
	 */
	private Room stanza2;

	/**
	 * Costruttore della classe
	 * 
	 * @param nome               Nome del collegamento
	 * @param stanzaPartenza     Stanza di partenza
	 * @param stanzaDestinazione Stanza destinazione
	 */
	Link(String nome, Room stanzaPartenza, Room stanzaDestinazione, ComportamentoApertura cp) {
		super(nome);
		stanza1 = stanzaPartenza;
		stanza2 = stanzaDestinazione;
		super.setCompApertura(cp);
	}
	
	/**
	 * Costruttore della classe
	 * 
	 * @param nome               Nome del collegamento
	 * @param stanzaPartenza     Stanza di partenza
	 * @param stanzaDestinazione Stanza destinazione
	 */
	Link(String nome, Room stanzaPartenza, Room stanzaDestinazione) {
		this(nome, stanzaPartenza,stanzaDestinazione, new ApriSenzaChiave());
	}

	/**
	 * Data una delle due stanze connesse fornisce il riferimento all'altra
	 * 
	 * @param startingRoom Stanza da cui si parte
	 * @return Stanza destinazione
	 * @throws DirezioneNonConsentitaException Se la Stringa fornita non è nessuna
	 *                                         delle due stanze connesse
	 */
	public Room getConnection(String startingRoom) throws DirezioneNonConsentitaException {
		if (stanza1.getNome().equals(startingRoom))
			return stanza2;
		else if (stanza2.getNome().equals(startingRoom))
			return stanza1;
		throw new DirezioneNonConsentitaException();
	}

	@Override
	public String toString() {
		return super.nome;
	}
	
	/**
	 * Permette di chiudere il link
	 */
	public void lock() {
		super.setCompApertura(new ApriConChiave());
	}
}
