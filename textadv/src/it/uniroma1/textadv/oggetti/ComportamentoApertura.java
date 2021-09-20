package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

/**
 * Interfaccia che rappresenta il comportamento che si ha quando si vuole aprire
 * un oggetto
 * 
 * @author matte
 *
 */
@FunctionalInterface
public interface ComportamentoApertura {

	/**
	 * Metodo che permette di aprire l'oggetto
	 * 
	 * @param c Oggetto "chiave"
	 * @param o Oggetto da aprire
	 * @return Stringa con il risultato dell'operazione
	 * @throws ChiaveNecessariaExeption Se serve una chiave per aprire
	 */
	String open(OggettoCheInteragisce c, Oggetto o) throws ChiaveNecessariaExeption;

}
