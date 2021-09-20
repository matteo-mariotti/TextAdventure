package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella una smerigliatrice
 * @author matte
 *
 */
public class Smerigliatrice extends OggettoCheInteragisce implements Takeable{

	
	/**
	 * Costruttore della classe
	 * @param nome Nome dell'oggetto
	 * @param inter Interazione
	 */
	public Smerigliatrice(String nome, Oggetto inter) {
		super(nome, inter);
	}

}
