package it.uniroma1.textadv.textEngine.verbs;

/**
 * Generica classe che rappresenta un verbo
 * @author matte
 *
 */
public abstract class Verbo {
	/**
	 * Se non specifica come usare il verbo
	 */
	private static String NOT_SPECIFIED = "Devi specificare come usare il verbo";

	/**
	 * Comportamento di default del verbo
	 * @return Stringa con il risultato
	 */
	public String esegui() {
		return NOT_SPECIFIED;
	}
	
}
