package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Entita;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Parla extends Verbo implements VerboUnitario{
	
	private static final String NOT_ALLOWED = "Non puoi parlare con ";
	
	public String esegui(String entita) {
		try {
			ElementoStanza e = ObjFinder.getArg(entita);
			if (e instanceof Entita)
				return ((Entita) e).speak();
			return NOT_ALLOWED + e.getNome();
		} catch (ElementoInesistenteException e1) {
			return Verbo.NON_TROVATO;
		}
	}

	
	
}
