package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Payable;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Dai extends Verbo {

	public void esegui(String elemento, String dest) throws OggettoInesistenteException {
		ElementiStanza e = ObjFinder.getArg(dest);
		if (e instanceof Payable) {
			try {
				((Payable) e).pagamento(elemento);
			} catch (OggettoInesistenteException | PagamentoNecessarioException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Non puoi pagare " + dest);
		}

	}

}
