package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella un cassa che può contenere un oggetto
 * @author matte
 *
 */
public class Cassa extends Box{
	/**
	 * Costruttore della classe
	 * @param nome Nome della cassa
	 * @param inter Oggetto contenuto nella cassa
	 */
	public Cassa(String nome, Oggetto inter) {
		super(nome, inter, new ApriSenzaChiave());
	}



}
