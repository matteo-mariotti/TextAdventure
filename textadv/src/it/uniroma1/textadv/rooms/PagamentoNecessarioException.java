package it.uniroma1.textadv.rooms;
import it.uniroma1.textadv.characters.Entita;
/**
 * Eccezione da generare quando è necessario pagare una entità per prendere l'oggetto
 * @author matte
 *
 */
@SuppressWarnings("serial")
public class PagamentoNecessarioException extends Exception {

	/**
	 * Costruttore dell'eccezione
	 * @param e Entità da pagare
 	 * @param s Messaggio
	 */
	public PagamentoNecessarioException(Entita e, String s) {
		super(s);
	}
	
	/**
	 * Costruttore dell'eccezione
	 * @param e Entità da pagare
	 */
	public PagamentoNecessarioException(Entita e) {
		super("Devi prima pagare " + e.getNome() + "!!");
	}
}

