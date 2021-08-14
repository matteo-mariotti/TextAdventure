package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.oggetti.Usable;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.CollegamentoInesistenteException;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Usa extends Verbo {

	public void esegui(String oggettoDaUsare, String oggettoDest) {
		// TODO Lancia una eccezione se non ho l'oggetto nell'inventario
		ElementiStanza ogg1 = (Oggetto) Giocatore.instanceOf().getInventario().get(oggettoDaUsare);
		try {
			ElementiStanza ogg = ObjFinder.getArg(oggettoDest);
			if (ogg1 instanceof Usable)
				((Usable) ogg1).use(ogg);
		} catch (OggettoInesistenteException e) {
			try {
				Link l = Giocatore.instanceOf().getStanza().getLink(oggettoDest);
				if (ogg1 instanceof Usable)
					((Usable) ogg1).use(l);
			} catch (CollegamentoInesistenteException e1) {
				System.out.println("L'oggetto/link che hai indicato non esiste");

			}
		}
	}

	public void esegui(String linkDaUsare) {
		Room l;
		try {
			l = Giocatore.instanceOf().getStanza().getDestRoom(linkDaUsare);
			Giocatore.instanceOf().setRoom(l);
			System.out.println("Ti trovi ora in: " + l.getNome());
		} catch (CollegamentoInesistenteException | DirezioneNonConsentitaException | ChiaveNecessariaExeption e) {
			System.out.println("Non esiste questa stanza/link!");

		}
	}
}
