package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Apri extends Verbo implements VerboUnitario, VerboBinario {

	private static final String NOT_OPENABLE = " non è un oggetto apribile";
	private static final String NO_KEY = "Non possiedi la chiave che vuoi usare!!";
	private static final String UNCORRECT_KEY = "Non stai usando la chiave corretta!";

	@Override
	public String esegui(String stringaInput) {
		return apri(stringaInput, null);
	}

	private String apri(String elemento, Oggetto chiave) {
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

		} catch (ChiaveNecessariaExeption e) {
			return UNCORRECT_KEY;
		}

	}

	@Override
	public String esegui(String oggettoDaAprire, String chiave) {
		Oggetto key = (Oggetto) Giocatore.instanceOf().getInventario().get(chiave);
		if (key == null)
			return NO_KEY;
		return apri(oggettoDaAprire, key);
	}
}
