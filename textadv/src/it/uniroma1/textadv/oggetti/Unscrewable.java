package it.uniroma1.textadv.oggetti;
/**
 * Interfaccia che rappresenta gli oggetti che possono essere svitati
 * @author matte 
 *
 */
@FunctionalInterface
public interface Unscrewable {

	/**
	 * Metodo che permette di svitare un oggetto
	 * @return Stringa che fornisce il risultato dell'operazione
	 */
	String svita();
	
}
