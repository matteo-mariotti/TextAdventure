package it.uniroma1.textadv.textEngine.verbs;


import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Breakable;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Rompi extends Verbo {
	
	public String esegui(String stringaInput){
		return rompi(stringaInput, null);
	}
	 
	private String rompi(String oggetto, Oggetto oggetto2) {
			try {
				ElementoStanza ogg = ObjFinder.getArg(oggetto);
				if (ogg instanceof Breakable) {
					if (oggetto2 == null)
						return ((Breakable) ogg).rompi();
					else
						return ((Breakable) ogg).rompi(oggetto2);
				}
				return "Non puoi rompere " + ogg.getNome();
			} catch (ElementoInesistenteException e) {
				return Verbo.NON_TROVATO;
	}}
	
	
	public String esegui(String stringaInput, String oggetto){
		Oggetto ogg = (Oggetto) Giocatore.instanceOf().getInventario().get(oggetto);
		return rompi(stringaInput, ogg);
	}
	
	
	

}
