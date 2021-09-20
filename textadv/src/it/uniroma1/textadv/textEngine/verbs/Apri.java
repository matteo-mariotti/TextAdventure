package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.OggettoCheInteragisce;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
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

	@Override
	public String esegui(String stringaInput) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException, DirezioneNonConsentitaException {
		return apri(stringaInput, null);
	}

	private String apri(String elemento, OggettoCheInteragisce chiave) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException, DirezioneNonConsentitaException {
			ElementoStanza ogg = ObjFinder.getArg(elemento);
			if (ogg instanceof Oggetto)
				return ((Oggetto)ogg).open(chiave);
			return "" + elemento + NOT_OPENABLE;

	}

	@Override
	public String esegui(String oggettoDaAprire, String chiave) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException, DirezioneNonConsentitaException {
		Oggetto key = (Oggetto) Giocatore.instanceOf().getInventario().get(chiave);
		if (key == null)
			return NO_KEY;
		if (key instanceof OggettoCheInteragisce)
			return apri(oggettoDaAprire, (OggettoCheInteragisce) key);
		return NO_KEY;
	}
}
