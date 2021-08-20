package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Entita;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Parla extends Verbo{
	
	public String esegui(String entita) {
		try {
			ElementiStanza e = ObjFinder.getArg(entita);
			if (e instanceof Entita) {
				return ((Entita) e).speak();
			}else
				return "Non puoi parlare con " + e.getNome();
		} catch (ElementoInesistenteException e1) {
			return Verbo.NON_TROVATO;
		}
	}

	
	
}
