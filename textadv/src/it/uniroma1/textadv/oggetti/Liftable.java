package it.uniroma1.textadv.oggetti;
/**
 * Interfaccia che indica gli oggetti che possono essere alzati
 * @author matte
 *
 */
@FunctionalInterface
public interface Liftable {

	/**
	 * Metodo che permette di alzare un oggetto
	 * @return Risultato dell'operazione
	 */
	String alza();

}

