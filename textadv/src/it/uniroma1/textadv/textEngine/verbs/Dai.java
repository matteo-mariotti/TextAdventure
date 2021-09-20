package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Payable;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Classe che rappresenta il verbo Dare
 * @author matte
 *
 */
public class Dai extends Verbo implements VerboBinario{

	
	@Override
	public String esegui(String elemento, String dest) throws PagamentoNecessarioException, ElementoInesistenteException, ImpossibileOttenereOggetto, ChiaveNecessariaExeption{
			ElementoStanza e = ObjFinder.getArg(dest);
			if (e instanceof Payable)
				return ((Payable) e).pagamento(elemento);
		return "Non puoi pagare " + dest;
	}

}
