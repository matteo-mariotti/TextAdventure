package it.uniroma1.textadv.textEngine.verbs;

import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Box;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.OggettoCheInteragisce;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Classe che modella l'azione del verbo Aprire
 * @author matte
 *
 */
public class Apri extends Verbo implements VerboUnitario, VerboBinario {

	/**
	 * Errore se prova ad aprire qualcosa che non si può aprire
	 */
	private static final String NOT_OPENABLE = " non è un oggetto apribile";
	/**
	 * Errore se non possiedo la chiave per aprire l'oggetto
	 */
	private static final String NO_KEY = "Non possiedi la chiave che vuoi usare!!";
	/**
	 * Errore se sto usando la chiave errata
	 */
	private static final String UNCORRECT_KEY = "Non stai usando la chiave corretta!";

	@Override
	public String esegui(String stringaInput) {
		return apri(stringaInput, null);
	}

	private String apri(String elemento, OggettoCheInteragisce chiave) {
		try {
			ElementoStanza ogg = ObjFinder.getArg(elemento);
			if (ogg instanceof Openable) {
				Openable o = (Openable) ogg;
				o.unlock(chiave);
				return o.open();
			}
			return "" + elemento + NOT_OPENABLE;
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;
		} catch (ImpossibileOttenereOggetto e) {
			return "Err";
		} catch (ChiaveNecessariaExeption e) {
			return UNCORRECT_KEY;
		} catch (PagamentoNecessarioException e) {
			return "Err";
		}

	}

	@Override
	public String esegui(String oggettoDaAprire, String chiave) {
		Oggetto key = (Oggetto) Giocatore.instanceOf().getInventario().get(chiave);
		if (key == null)
			return NO_KEY;
		if (key instanceof OggettoCheInteragisce)
			return apri(oggettoDaAprire, (OggettoCheInteragisce) key);
		return NO_KEY;
	}
}
