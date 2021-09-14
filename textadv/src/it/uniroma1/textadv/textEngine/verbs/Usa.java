package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Usable;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Verbo Usare
 * @author matte
 *
 */
public class Usa extends Verbo implements VerboUnitario, VerboBinario{

	/**
	 * Errore se non possiedo l'oggetto che voglio usare
	 */
	private static String NO_OBJ = "Non possiedi l'oggetto che vuoi usare";
	/**
	 * Errore se l'oggetto non si può usare
	 */
	private static String NOT_USABLE = "Non puoi usare questo oggetto";
	
	@Override
	public String esegui(String oggettoDaUsare, String oggettoDest) {
		if (!Giocatore.instanceOf().getInventario().containsKey(oggettoDaUsare))
			return NO_OBJ;
		ElementoStanza ogg;
		ElementoStanza ogg1 = (Oggetto) Giocatore.instanceOf().getInventario().get(oggettoDaUsare);
		try {
			 ogg = ObjFinder.getArg(oggettoDest);
			 return exec(ogg1, ogg);
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;
		} catch (ImpossibileOttenereOggetto e) {
			return "Err";
		} catch (ChiaveNecessariaExeption e) {
			return "Err";
		} catch (PagamentoNecessarioException e) {
			return "Err";
		}
	}
	
	private String exec(ElementoStanza usable, ElementoStanza dest){
		if (usable instanceof Usable)
			return ((Usable) usable).use(dest);
		return NOT_USABLE;
	}

	@Override
	public String esegui(String linkDaUsare) {
		try {
			Room l = Giocatore.instanceOf().getStanza().getDestRoom(linkDaUsare);
			return Giocatore.instanceOf().setRoom(l);
		} catch (ElementoInesistenteException | DirezioneNonConsentitaException e) {
			return Verbo.NON_TROVATO;
		} catch (ChiaveNecessariaExeption e) {
			return "Ti serve la chiave corretta!!";
		}
	}
}
