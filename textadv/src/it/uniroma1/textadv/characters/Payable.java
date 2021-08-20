package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

public interface Payable {

	String pagamento(String e) throws PagamentoNecessarioException;
	
}
