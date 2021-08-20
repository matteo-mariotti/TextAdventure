package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Payable;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Dai extends Verbo implements VerboBinario{

	
	@Override
	public String esegui(String elemento, String dest){
		try {
			ElementiStanza e = ObjFinder.getArg(dest);
			if (e instanceof Payable)
				return ((Payable) e).pagamento(elemento);
		return "Non puoi pagare " + dest;
		}	catch (ElementoInesistenteException e2) {
			return Verbo.NON_TROVATO;
		} catch (PagamentoNecessarioException e1) {
			return "Devi pagare " + e1.getNomeOwner() + " correttamente per prendere questo oggetto";
		}


	}

}
