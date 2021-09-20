package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella un generico elemento Cibo
 * @author matte
 *
 */
public class Cibo extends Oggetto implements Takeable{

	/**
	 * Costruttore della classe
	 * @param nome Nome del cibo
	 */
	public Cibo(String nome) {
		super(nome);
	}

}
