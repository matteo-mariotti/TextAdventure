package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Usable;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Usa extends Verbo{
	
	public void esegui(String oggettoDaUsare, String oggettoDest){
		ElementiStanza ogg1 = (Oggetto) Giocatore.instanceOf().getInventario().get(oggettoDaUsare);
		try {
			ElementiStanza ogg = ObjFinder.getArg(oggettoDest);
			if (ogg1 instanceof Usable)
				((Usable) ogg1).use(ogg);
			else
				System.out.println("Err");
		} catch (OggettoInesistenteException e) {
			System.out.println("L'oggetto che hai indicato non esiste");
		}
	}

}
