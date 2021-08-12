package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Guarda extends Verbo {

	public void esegui(String elemento) throws OggettoInesistenteException {
		ElementiStanza ogg = ObjFinder.getArg(elemento);
		System.out.println(ogg.describe());
	}
	
	public void esegui() {
		System.out.println(Giocatore.instanceOf().getStanza().describe());
	}
}
