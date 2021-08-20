package it.uniroma1.textadv.textEngine.verbs;


import it.uniroma1.textadv.ElementiStanza;
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
				ElementiStanza ogg = ObjFinder.getArg(oggetto);
				if (ogg instanceof Breakable) {
					Breakable o = (Breakable) ogg;
					if (oggetto2 == null)
						return o.rompi();
					else
						return o.rompi(oggetto2);
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
