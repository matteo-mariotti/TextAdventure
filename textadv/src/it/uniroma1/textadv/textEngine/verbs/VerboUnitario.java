package it.uniroma1.textadv.textEngine.verbs;
/**
 * Interfaccia funzionale che rappresenta i verbi che prendono un solo argomento
 * @author matte
 *
 */
@FunctionalInterface
public interface VerboUnitario {

	/**
	 * Cosa fa il verbo
	 * @param arg1 Argomento 1
	 * @return Stringa con il risultato dell'operazione
	 */
	String esegui(String arg1);

}
