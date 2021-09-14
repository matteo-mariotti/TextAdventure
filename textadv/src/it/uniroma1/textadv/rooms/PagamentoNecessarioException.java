package it.uniroma1.textadv.rooms;
import it.uniroma1.textadv.characters.Entita;
/**
 * Eccezione da generare quando � necessario pagare una entit� per prendere l'oggetto
 * @author matte
 *
 */
@SuppressWarnings("serial")
public class PagamentoNecessarioException extends Exception {

	/**
	 * Costruttore dell'eccezione
	 * @param e Entit� da pagare
 	 * @param s Messaggio
	 */
	public PagamentoNecessarioException(Entita e, String s) {
		super(s);
	}
	
	/**
	 * Costruttore dell'eccezione
	 * @param e Entit� da pagare
	 */
	public PagamentoNecessarioException(Entita e) {
		super("Devi prima pagare " + e.getNome() + "!!");
	}
}

