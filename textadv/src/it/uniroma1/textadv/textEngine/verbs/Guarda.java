package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Guarda extends Verbo {

	public void esegui(String elemento) throws OggettoInesistenteException {
		Object ogg = ObjFinder.getArg(elemento);
		System.out.println(ogg.toString());
	}
	
	public void esegui(Oggetto o, Oggetto o1) {
		System.out.println(o.toString() + o1.toString());
	}
	
	public void esegui() {
		System.out.println(Giocatore.instanceOf().getStanza().toString());
	}
}
