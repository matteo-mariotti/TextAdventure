package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Apri extends Verbo implements VerboUnitario, VerboBinario {

	private static String NOT_OPENABLE = " non è un oggetto apribile";
	private static String NO_KEY = "Non possiedi la chiave che vuoi usare!!";

	@Override
	public String esegui(String stringaInput) {
		return apri(stringaInput, null);
	}

	private String apri(String elemento, Oggetto chiave) {
		try {
			ElementiStanza ogg = ObjFinder.getArg(elemento);
			if (ogg instanceof Openable) {
				Openable o = (Openable) ogg;
				o.unlock(chiave);
				return o.open();
			}
			return "" + elemento + NOT_OPENABLE;
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;

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
