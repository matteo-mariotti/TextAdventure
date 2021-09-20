package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.OggettoCheInteragisce;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che modella l'oggetto teletrasporto del gioco
 * @author matte
 *
 */
public class Teletrasporto extends Link{
	
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
	public String open(OggettoCheInteragisce c) throws ChiaveNecessariaExeption {
		super.open(c);
		try {
			return Giocatore.instanceOf().setRoom(super.getConnection(Giocatore.instanceOf().getStanza().getNome()));
		} catch (DirezioneNonConsentitaException e) {
			return "Si è verificato un errore";
		}
	}

}
