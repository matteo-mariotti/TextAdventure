package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Animale;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Accarezza extends Verbo implements VerboUnitario {

	@Override
	public String esegui(String entita) {
		try {
			ElementiStanza ent = ObjFinder.getArg(entita);
			if (ent instanceof Animale) {
				Animale a = (Animale) ent;
				return a.accarezza();
			}
			return "Non puoi accarezzare " + entita + "!!!";
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;
		}
	}

}
