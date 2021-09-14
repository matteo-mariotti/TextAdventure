package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
/**
 * Interfaccia che rappresenta gli oggetti che possono essere usati
 * @author matte
 *
 */
public interface Usable {

	/**
	 * Metodo per usare un oggetto in maniera generica (es. usa martello)
	 * @return Stringa con il risultato dell'operazione
	 */
	String use();
	
	/**
	 * Metodo che permette di usare un oggetto su un altro elemento della stanza
	 * @param e Elemento su cui usare l'oggetto
	 * @return Stringa con il risultato dell'operazione
	 */
	String use(ElementoStanza e);
}
