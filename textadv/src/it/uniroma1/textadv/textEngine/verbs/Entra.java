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

	@Override
	public String esegui(String stanza) throws ElementoInesistenteException, DirezioneNonConsentitaException, ChiaveNecessariaExeption {
		Room l = Giocatore.instanceOf().getStanza().getDestRoom(stanza);
			return Giocatore.instanceOf().setRoom(l);

	}

}
