package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.oggetti.Usable;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Usa extends Verbo {

	public String esegui(String oggettoDaUsare, String oggettoDest) {
		ElementoStanza ogg1 = (Oggetto) Giocatore.instanceOf().getInventario().get(oggettoDaUsare);
		try {
			ElementoStanza ogg = ObjFinder.getArg(oggettoDest);
			if (ogg1 instanceof Usable)
				return ((Usable) ogg1).use(ogg);
			return "Non puoi usare questo oggetto";
		} catch (ElementoInesistenteException e) {
			try {
				Link l = Giocatore.instanceOf().getStanza().getLink(oggettoDest);
				if (ogg1 instanceof Usable)
					return ((Usable) ogg1).use(l);
				return "Non puoi usare questo oggetto"; 
			} catch (ElementoInesistenteException e1) {
				return ("L'oggetto/link che hai indicato non esiste");
			}
		}
	}

	public String esegui(String linkDaUsare) {
		try {
			Room l = Giocatore.instanceOf().getStanza().getDestRoom(linkDaUsare);
			return Giocatore.instanceOf().setRoom(l);
		} catch (ElementoInesistenteException | DirezioneNonConsentitaException | ChiaveNecessariaExeption e) {
			return "Non esiste questa stanza/link!";

		}
	}
}
