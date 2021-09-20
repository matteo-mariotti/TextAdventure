package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Usable;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Verbo Usare
 * 
 * @author matte
 *
 */
public class Usa extends Verbo implements VerboUnitario, VerboBinario {

	/**
	 * Errore se non possiedo l'oggetto che voglio usare
	 */
	private static String NO_OBJ = "Non possiedi l'oggetto che vuoi usare";
	/**
	 * Errore se l'oggetto non si può usare
	 */
	private static String NOT_USABLE = "Non puoi usare questo oggetto";

	@Override
	public String esegui(String oggettoDaUsare, String oggettoDest) throws ImpossibileOttenereOggetto,
			ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException {
		if (!Giocatore.instanceOf().getInventario().containsKey(oggettoDaUsare))
			return NO_OBJ;
		ElementoStanza ogg1 = (Oggetto) Giocatore.instanceOf().getInventario().get(oggettoDaUsare);
		ElementoStanza ogg = ObjFinder.getArg(oggettoDest);
		return exec(ogg1, ogg);
	}

	private String exec(ElementoStanza usable, ElementoStanza dest) {
		if (usable instanceof Usable)
			if (dest != null)
				return ((Usable) usable).use(dest);
			else
				return ((Usable) usable).use();
		return NOT_USABLE;
	}

	@Override
	public String esegui(String linkDaUsare) throws ElementoInesistenteException, DirezioneNonConsentitaException,
			ChiaveNecessariaExeption, ImpossibileOttenereOggetto, PagamentoNecessarioException {
		ElementoStanza ogg;
		if (Giocatore.instanceOf().getInventario().containsKey(linkDaUsare))
			ogg = Giocatore.instanceOf().getInventario().get(linkDaUsare);
		else
			ogg = ObjFinder.getArg(linkDaUsare);
		if (ogg instanceof Link) {
			Room l = Giocatore.instanceOf().getStanza().getDestRoom(((Link) ogg).getNome());
			return Giocatore.instanceOf().setRoom(l);
		}
		return exec(ogg, null);
	}
}
