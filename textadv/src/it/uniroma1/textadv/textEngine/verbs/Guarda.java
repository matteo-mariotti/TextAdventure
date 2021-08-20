package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Guarda extends Verbo {

	public String esegui(String elemento){
		ElementiStanza ogg;
		try {
			ogg = ObjFinder.getArg(elemento);
			return ogg.describe();
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;
		}
	}
	
	public String esegui() {
		return Giocatore.instanceOf().getStanza().describe();
	}
}
