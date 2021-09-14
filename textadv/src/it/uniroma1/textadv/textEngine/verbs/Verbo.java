package it.uniroma1.textadv.textEngine.verbs;

/**
 * Generica classe che rappresenta un verbo
 * @author matte
 *
 */
public abstract class Verbo {

	/**
	 * Errore se non trovo ciò con cui vuole interagire il giocatore
	 */
	protected static String NON_TROVATO = "Ciò che stai cercando di usare non esiste!!";
	/**
	 * Se non specifica come usare il verbo
	 */
	protected static String NOT_SPECIFIED = "Devi specificare come usare il verbo";

	/**
	 * Comportamento di default del verbo
	 * @return Stringa con il risultato
	 */
	public String esegui() {
		return NOT_SPECIFIED;
	}
	
}
