package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public interface Payable {

	void pagamento(String e) throws OggettoInesistenteException, PagamentoNecessarioException;
	
}
