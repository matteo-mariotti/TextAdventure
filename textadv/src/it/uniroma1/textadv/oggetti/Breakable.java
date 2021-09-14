package it.uniroma1.textadv.oggetti;
/**
 * Interfaccia che rappresenta gli oggetti che possono essere rotti
 * @author matte
 *
 */
public interface Breakable {

	/**
	 * Metodo che permette di rompere qualcosa con un oggetto
	 * @param ogg Oggetto da usare per rompere
	 * @return Stringa con il risultato dell'operazione
	 */
	String rompi(Oggetto ogg);
	
	/**
	 * Metodo per rompere un oggetto
	 * @return Stringa con il risultato dell'operazione
	 */
	String rompi();
}
