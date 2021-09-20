package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella un cassetto
 * @author matte
 *
 */
public class Cassetto extends Box{
	
	/**
	 * Costruttore della classe
	 * @param nome Nome dell'oggetto
	 * @param inter Oggetto contenuto
	 */
	public Cassetto(String nome, Oggetto inter) {
		super(nome, inter, new ApriSenzaChiave());
	}

	
}