package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;

public class Entra extends Verbo implements VerboUnitario {

	@Override
	public String esegui(String stanza) {
		Room l;
		try {
			l = Giocatore.instanceOf().getStanza().getDestRoom(stanza);
			return Giocatore.instanceOf().setRoom(l);
		} catch (ChiaveNecessariaExeption e2) {
			return "Ti serve una chiave per aprire questa stanza";
		} catch (ElementoInesistenteException | DirezioneNonConsentitaException e1) {
			return "Non esiste questa stanza/link!";
		}

	}

}
