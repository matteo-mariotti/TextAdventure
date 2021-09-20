package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella un letto contenitore
 * @author matte
 *
 */
public class Letto extends Box{

	/**
	 * Costruttore della classe
	 * @param nome Nome del letto
	 * @param inter Oggetto contenuto
	 */
	public Letto(String nome, Oggetto inter) {
		super(nome, inter, new ApriSenzaChiave());
	}

	/**
	 * Costruttore della classe
	 * @param nome Nome del letto
	 */
	public Letto(String nome) {
		this(nome, null);
	}
}
