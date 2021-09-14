package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che modella il verbo entrare
 * @author matte
 *
 */
public class Entra extends Verbo implements VerboUnitario {

	/**
	 * Errore se non possiedo la chiave per aprire l'oggetto
	 */
	private static final String NO_KEY = "Ti serve una chiave per aprire questa stanza!!";
	
	@Override
	public String esegui(String stanza) {
		Room l;
		try {
			l = Giocatore.instanceOf().getStanza().getDestRoom(stanza);
			return Giocatore.instanceOf().setRoom(l);
		} catch (ChiaveNecessariaExeption e2) {
			return NO_KEY;
		} catch (ElementoInesistenteException | DirezioneNonConsentitaException e1) {
			return Verbo.NON_TROVATO;
		}

	}

}
