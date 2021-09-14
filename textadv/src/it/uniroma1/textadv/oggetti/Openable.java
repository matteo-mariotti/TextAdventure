package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

/**
 * Interfaccia che rappresenta gli oggetti che possono essere aperti
 * 
 * @author matte
 *
 */
public interface Openable {

	/**
	 * Metodo che permette di aprire
	 * 
	 * @return Stringa con il risultato dell'operazione
	 */
	String open();

	/**
	 * Metodo che permette di sbloccare l'oggetto
	 * 
	 * @param ogg Oggetto chiave con cui aprire
	 * @return True se l'operazione di apertura è riuscita
	 * @throws ChiaveNecessariaExeption Se viene fornita una chiave errata
	 */
	boolean unlock(OggettoCheInteragisce ogg) throws ChiaveNecessariaExeption;

}
