package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.links.Link;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.rooms.CollegamentoInesistenteException;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Apri extends Verbo {

	public void esegui(String stringaInput) throws OggettoInesistenteException, CollegamentoInesistenteException {
		try {
			Object ogg = ObjFinder.getArg(stringaInput);
			if (ogg instanceof Openable) {
				Openable o = (Openable) ogg;
				o.open();
			}
		} catch (OggettoInesistenteException e) {
			// Vedo se si tratta di un link
			try {
			Link l = Giocatore.instanceOf().getStanza().getLink(stringaInput);
			if (l instanceof Openable) {
				Openable o = (Openable) l;
				o.open();
			}
			}
			catch(CollegamentoInesistenteException e1)
			{
				System.out.println("Il collegamento che cerchi di aprire è inesistente");
			}
		}
	}
}
