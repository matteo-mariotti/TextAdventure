package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella una scrivania
 * @author matte
 *
 */
public class Scrivania extends Box {

	/**
	 * Costruttore della classe Scrivania
	 * @param nome Nome della scrivania
	 * @param inter Oggetto contenuto nella scrivania
	 */
	public Scrivania(String nome, Oggetto inter) {
		super(nome, inter, new ApriSenzaChiave());
	}
}
