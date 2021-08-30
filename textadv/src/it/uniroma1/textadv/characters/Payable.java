package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

/**
 * Interfaccia funzionale che indica coloro che possono essere pagati
 * @author matte
 *
 */
@FunctionalInterface
public interface Payable {

	/**
	 * Metodo che permette di pagare
	 * @param e Pagamento fornito
	 * @return Stringa con il risultato dell'operazione
	 * @throws PagamentoNecessarioException
	 * @throws ElementoInesistenteException 
	 */
	String pagamento(String e) throws PagamentoNecessarioException, ElementoInesistenteException;
	
}
