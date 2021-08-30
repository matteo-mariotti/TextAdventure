package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Animale;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Accarezza extends Verbo implements VerboUnitario {

	private static final String NON_ANIMALE = "Non puoi accarezzarlo!";
	
	@Override
	public String esegui(String entita) {
		try {
			ElementoStanza ent = ObjFinder.getArg(entita);
			if (ent instanceof Animale) {
				Animale a = (Animale) ent;
				return a.accarezza();
			}
			return NON_ANIMALE;
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;
		}
	}

}
