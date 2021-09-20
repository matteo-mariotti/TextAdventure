package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Cibo;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;
/**
 * Classe che modella il verbo mangiare
 * @author matte
 *
 */
public class Mangia extends Verbo implements VerboUnitario {
	
	@Override
	public String esegui(String elemento) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException{
			ElementoStanza ogg = ObjFinder.getArg(elemento);
			if (ogg instanceof Cibo) {
				Giocatore.instanceOf().getStanza().remove(ogg);
				return Giocatore.instanceOf().mangia(((Cibo)ogg));}
			return "Non puoi mangiare " + elemento;
	}

}
