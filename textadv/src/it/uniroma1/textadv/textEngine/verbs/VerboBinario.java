package it.uniroma1.textadv.textEngine.verbs;
/**
 * Interfaccia funzionale che rappresenta i verbi che prendono due argomenti
 * @author matte
 *
 */
@FunctionalInterface
public interface VerboBinario {
	/**
	 * Cosa fa il verbo
	 * @param arg1 Argomento 1
	 * @param arg2 Argomento 2
	 * @return Stringa con il risultato dell'operazione
	 */
	String esegui(String arg1, String arg2);

}
