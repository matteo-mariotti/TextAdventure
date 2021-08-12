package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Breakable;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Rompi extends Verbo {
	
	public void esegui(String stringaInput){
		try {
			ElementiStanza ogg = ObjFinder.getArg(stringaInput);
			if (ogg instanceof Breakable) {
				Breakable o = (Breakable) ogg;
				o.rompi();
			}
		} catch (OggettoInesistenteException e) {
			System.out.println("L'oggetto che hai indicato non esiste");
		}
	}
	
	
	public void esegui(String stringaInput, String oggetto){
		Oggetto key = (Oggetto) Giocatore.instanceOf().getInventario().get(oggetto);
		try {
			ElementiStanza ogg = ObjFinder.getArg(stringaInput);
			if (ogg instanceof Breakable) {
				Breakable o = (Breakable) ogg;
				o.rompi(key);
			}
		} catch (OggettoInesistenteException e) {
			System.out.println("L'oggetto che hai indicato non esiste");
		}
	}
	
	
	

}
