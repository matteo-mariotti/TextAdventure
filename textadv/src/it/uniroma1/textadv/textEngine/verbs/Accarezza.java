package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Animale;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Accarezza extends Verbo{

	public void esegui(String entita) throws OggettoInesistenteException {
		Object ent = ObjFinder.getArg(entita);
		if (ent instanceof Animale) {
			Animale a = (Animale) ent;
			a.accarezza();
		}else
		{
			System.out.println("Non puoi accarezzare " + entita + "!!!");
		}
	}
	
}
