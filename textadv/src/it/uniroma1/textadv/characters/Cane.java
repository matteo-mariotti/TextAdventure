package it.uniroma1.textadv.characters;

/**
 * Classe che modella un cane
 * 
 * @author matte
 *
 */
public class Cane extends Animale {

	/**
	 * Costruttore del cane
	 * 
	 * @param nome Nome del cane
	 */
	public Cane(String nome) {
		super(nome, new Abbaia(), new Abbaia());
	}
	
}
