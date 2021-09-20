package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Classe che modella il verbo guardare
 * @author matte
 *
 */
public class Guarda extends Verbo implements VerboUnitario{

	@Override
	public String esegui(String elemento) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException{
			ElementoStanza ogg = ObjFinder.getArg(elemento);
			return ogg.describe();
	}
	
	@Override
	public String esegui() {
		return Giocatore.instanceOf().getStanza().describe();
	}
}
